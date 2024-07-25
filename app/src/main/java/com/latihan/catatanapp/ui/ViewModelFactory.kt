package com.latihan.catatanapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.latihan.catatanapp.di.Injection
import com.latihan.catatanapp.repository.NoteRepository
import com.latihan.catatanapp.ui.main.MainViewModel
import com.latihan.catatanapp.ui.noteup.NoteAddUpdateViewModel

/**
 * Kelas ViewModelFactory bertanggung jawab untuk membuat instance dari ViewModel.
 * Kelas ini menggunakan pola Singleton untuk memastikan hanya ada satu instance dari ViewModelFactory yang dibuat.
 *
 * @param repository Repository yang digunakan oleh ViewModel untuk berinteraksi dengan data.
 */
class ViewModelFactory(
    private val repository: NoteRepository,
) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Membuat instance dari ViewModel yang ditentukan.
     *
     * @param modelClass Kelas dari ViewModel yang akan dibuat.
     * @return Instance dari ViewModel yang diminta.
     * @throws IllegalArgumentException Jika kelas ViewModel yang diminta tidak dikenal.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            MainViewModel::class.java -> MainViewModel(repository)
            NoteAddUpdateViewModel::class.java -> NoteAddUpdateViewModel(repository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } as T

    /**
     * Companion object yang digunakan untuk mengimplementasikan pola Singleton.
     * Menyediakan metode untuk mendapatkan instance dari ViewModelFactory.
     */
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        /**
         * Mendapatkan instance dari ViewModelFactory.
         * Jika instance belum dibuat, akan dibuat instance baru dengan repository yang diberikan oleh Injection.
         *
         * @param context Context yang digunakan untuk mendapatkan repository.
         * @return Instance dari ViewModelFactory.
         */
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}