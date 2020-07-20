package com.denizd.imfine.data

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.denizd.imfine.model.Entry
import com.denizd.imfine.util.toDate
import kotlinx.coroutines.coroutineScope
import java.util.*

class Repository private constructor(applicationContext: Context) {

    private val db: AppDao = AppDatabase.get(applicationContext).db()
    private val prefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(applicationContext)

    companion object {
        private lateinit var instance: Repository
        fun get(applicationContext: Context? = null): Repository {
            if (!::instance.isInitialized) synchronized(Repository::class) {
                applicationContext?.let { c ->
                    instance = Repository(c)
                } ?: throw IllegalStateException("You must call get() using a context once before calling it without parameters")
            }
            return instance
        }
    }

    val allEntries: LiveData<List<Entry>> = db.allEntries
    val latestEntries: LiveData<List<Entry>> = db.latestEntries

    fun save(entry: Entry) {
        db.insert(entry)
    }

    /**
     * Checks whether the user has created an entry for the day, and resets the counter using
     * [resetHourlyEntry] if not.
     *
     * @return whether the user has created an entry today (same value as [hasCreatedHourlyEntry])
     *
     * FIXME: comparison only works if the dates for the respective days are different;
     *      2020-07-19 != 2020-07-20, but 2020-07-19 == 2020-08-19
     */
    suspend fun checkHourlyEntry(): Boolean =
        (System.currentTimeMillis() - coroutineScope { db.getLatestEntryTime() } < 3600000).also { result ->
            if (!result) resetHourlyEntry()
        }

    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        edit().apply {
            action()
            apply()
        }
    }

    fun hasCreatedHourlyEntry(): Boolean = prefs.getBoolean("hasCreatedHourlyEntry", false)
    private fun resetHourlyEntry() = prefs.edit {
        putBoolean("hasCreatedEntryToday", false)
    }

    fun getUsername(): String = prefs.getString("username", "") ?: ""
    fun setUsername(newName: String) = prefs.edit {
        putString("username", newName)
    }

    suspend fun getJson(): String {
        var result = """
            {
                "entries" : [
        """.trimIndent()
        coroutineScope { db.getEntries() }.forEachIndexed { index, entry ->
            // i know this doesn't look good
            result +=
"""${if (index == 0) "" else ","}
        {
            "rating" : ${entry.rating},
            "description" : "${entry.description}",
            "time" : ${entry.time}
        }"""

        }
        result += """
                
                ]
            }
        """.trimIndent()
        return result
    }
}