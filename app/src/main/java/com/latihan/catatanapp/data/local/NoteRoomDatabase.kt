package com.latihan.catatanapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database for managing Note entities.
 */
@Database(entities = [Note::class], version = 2)
abstract class NoteRoomDatabase : RoomDatabase() {

    /**
     * Provides access to the NoteDao.
     */
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null
        /**
         * Retrieves the singleton instance of the database.
         * @param context The application context.
         * @return The database instance.
         */
        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteRoomDatabase::class.java, "note_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as NoteRoomDatabase
        }
    }
}