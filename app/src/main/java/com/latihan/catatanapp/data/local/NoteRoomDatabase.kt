package com.latihan.catatanapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.data.local.note.NoteDao

/**
 * Database Room untuk mengelola entitas Note.
 */
@Database(entities = [Note::class], version = 2)
abstract class NoteRoomDatabase : RoomDatabase() {

    /**
     * Menyediakan akses ke NoteDao.
     */
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        /**
         * Mendapatkan instance tunggal dari database.
         * @param context Konteks aplikasi.
         * @return Instance database.
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
