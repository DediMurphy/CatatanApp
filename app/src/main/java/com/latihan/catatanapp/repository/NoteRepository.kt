package com.latihan.catatanapp.repository

import androidx.lifecycle.LiveData
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.data.local.note.NoteDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Repository for accessing Note data.
 */
class NoteRepository(
    private var mNotesDao: NoteDao
) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * Mengambil semua notes
     * @return A LiveData list of all notes.
     */
    fun getAllNotes(): LiveData<List<Note>> = mNotesDao.getAllNotes()

    /**
     * Mengambil notes berdasarkan title menggunakan bubble sort
     * @return A LiveData list of note ordered title.
     */
    fun getNotesByTitle() : LiveData<List<Note>> {
        val notes  = mNotesDao.getNotesByTitle()
        if (notes.value != null) {
            bubbleSort(notes.value!!.toMutableList())
        }
        return notes
    }

    /**
     * Mengambil notes berdasarkan date
     * @return A LiveData list of note ordered date
     */
    fun getNotesByDate() : LiveData<List<Note>> = mNotesDao.getNotesByDate()

    /**
     * Menambahkan note baru
     * @param notes the note to insert
     */
    fun insert(notes: Note) {
        executorService.execute{ mNotesDao.insert(notes)}
    }

    /**
     * Menghapus note
     * @param notes the note to insert
     */
    fun delete(notes: Note) {
        executorService.execute{ mNotesDao.delete(notes)}
    }

    /**
     * Mengupdate note
     * @param notes the note to update
     */
    fun update(notes: Note) {
        executorService.execute{ mNotesDao.update(notes)}
    }

    /**
     * Mengurutkan daftar objek Note berdasarkan atribut title menggunakan algoritma Bubble Sort.
     *
     * @param data MutableList<Note> - Daftar yang akan diurutkan.
     */
    private fun bubbleSort(data: MutableList<Note>) {
        var isSorted = false
        for (i in 0 until data.lastIndex) {
            if (!isSorted) {
                isSorted = true
                for (j in 0 until data.lastIndex - i) {
                    if (data[j].title!! > data[j + 1].title!!) {
                        val temp = data[j]
                        data[j] = data[j + 1]
                        data[j + 1] = temp
                        isSorted = false
                    }
                }
            }
        }
    }

    /**
     * Singleton untuk NoteRepository.
     *
     * Menggunakan pola singleton thread-safe untuk memastikan bahwa hanya ada satu instance
     * dari NoteRepository yang dibuat.
     */
    companion object {
        @Volatile
        private var instance: NoteRepository? = null

        /**
         * Mendapatkan instance dari NoteRepository.
         * Jika instance belum ada, membuatnya dalam blok yang disinkronkan.
         *
         * @param noteDao NoteDao - Data Access Object untuk Note.
         * @return NoteRepository - Instance dari NoteRepository.
         */
        fun getInstance(
            noteDao: NoteDao
        ): NoteRepository =
            instance ?: synchronized(this) {
                instance ?: NoteRepository(noteDao)
            }.also { instance = it }
    }
}