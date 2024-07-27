package com.latihan.catatanapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.data.local.note.User
import com.latihan.catatanapp.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * Class ViewModel untuk mengelola dan mengakses data note
 *
 * @property mNoteRepository repository untuk mengakses data note
 */
class MainViewModel(private val mNoteRepository: NoteRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

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

    fun searchNotesByTitle(query: String): LiveData<List<Note>> = mNoteRepository.searchNotesByTitle(query)

    /**
     * Mengambil notes berdasarkan date dari repository
     *
     * @return LiveData list of notes sorted by date.
     */
    fun getNoteByDate(): LiveData<List<Note>> = mNoteRepository.getNotesByDate()

}