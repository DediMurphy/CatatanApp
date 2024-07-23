package com.latihan.catatanapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.catatanapp.data.local.Note
import com.latihan.catatanapp.repository.NoteRepository

/**
 * Class ViewModel untuk mengelola dan mengakses data note
 *
 * @property mNoteRepository repository untuk mengakses data note
 */
class MainViewModel(private val mNoteRepository: NoteRepository) : ViewModel() {

    /**
     * Mengambil semua notes dari repository
     *
     * @return Livedata list of all notes.
     */
    fun getAllNotes(): LiveData<List<Note>> = mNoteRepository.getAllNotes()

    /**
     * Mengambil notes berdasarkan title dari repository
     *
     * @return LiveData list of notes sorted by title.
     */
    fun getSortedNotesByTitle(): LiveData<List<Note>> = mNoteRepository.getNotesByTitle()

    /**
     * Mengambil notes berdasarkan date dari repository
     *
     * @return LiveData list of notes sorted by date.
     */
    fun getNoteByDate(): LiveData<List<Note>> = mNoteRepository.getNotesByDate()
}