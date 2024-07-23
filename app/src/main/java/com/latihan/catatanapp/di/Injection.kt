package com.latihan.catatanapp.di

import android.content.Context
import com.latihan.catatanapp.data.local.NoteRoomDatabase
import com.latihan.catatanapp.repository.NoteRepository

/**
 * **Injection**
 *
 * Kelas ini bertanggung jawab untuk menyediakan instance dari `NoteRepository`.
 * Ini memanfaatkan pola desain *dependency injection* untuk menyuntikkan dependensi yang diperlukan.
 *
 * @see NoteRepository
 * @see NoteRoomDatabase
 */
object Injection {

    /**
     * **provideRepository**
     *
     * Menghasilkan instance dari `NoteRepository` yang terhubung dengan `NoteDao` dari `NoteRoomDatabase`.
     * Metode ini memerlukan konteks aplikasi untuk mengakses database dan kemudian menyediakan repository.
     *
     * @param context Konteks aplikasi yang digunakan untuk mengakses `NoteRoomDatabase`.
     * @return Instance dari `NoteRepository` yang siap digunakan.
     */
    fun provideRepository(context: Context): NoteRepository {
        // Mendapatkan instance dari NoteRoomDatabase
        val database = NoteRoomDatabase.getDatabase(context)

        // Mengembalikan instance dari NoteRepository dengan NoteDao dari database
        return NoteRepository.getInstance(database.noteDao())
    }
}
