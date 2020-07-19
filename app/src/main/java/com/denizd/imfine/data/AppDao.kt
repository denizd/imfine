package com.denizd.imfine.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denizd.imfine.model.Entry

@Dao
interface AppDao {

    @get:Query("SELECT * FROM entry ORDER BY time DESC")
    val allEntries: LiveData<List<Entry>>

    @Query("SELECT MAX(time) FROM entry")
    fun getLatestEntryTime(): Long

    @Insert
    fun insert(entry: Entry): Long

    @get:Query("SELECT * FROM entry ORDER BY time DESC LIMIT 5")
    val latestEntries: LiveData<List<Entry>>
}