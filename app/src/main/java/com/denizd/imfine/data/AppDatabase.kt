package com.denizd.imfine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.denizd.imfine.model.Entry

@Database(entities = [Entry::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun db(): AppDao

    companion object {
        private val migrationRaiseRating: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("UPDATE entry SET rating = rating + 1")
            }
        }

        private lateinit var instance: AppDatabase
        @JvmStatic
        fun get(applicationContext: Context): AppDatabase {
            if (!::instance.isInitialized) synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "imfine_db"
                )
                    .addMigrations(migrationRaiseRating)
                    .build()
            }
            return instance
        }
    }
}