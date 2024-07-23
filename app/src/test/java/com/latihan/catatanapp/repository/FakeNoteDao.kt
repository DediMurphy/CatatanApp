package com.latihan.catatanapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.catatanapp.data.local.Note
import com.latihan.catatanapp.data.local.NoteDao

class FakeNoteDao : NoteDao {
    private val notes = mutableListOf<Note>()
    private val observableNotes = MutableLiveData<List<Note>>(notes)

    override fun insert(note: Note) {
        notes.add(note)
        refreshLiveData()
    }

    override fun delete(note: Note) {
        notes.removeAll { it.id == note.id }
        refreshLiveData()
    }

    override fun update(note: Note) {
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
            refreshLiveData()
        }
    }

    override fun getAllNotes(): LiveData<List<Note>> = observableNotes

    override fun getNotesByTitle(): LiveData<List<Note>> {
        val sortedNotes = notes.sortedBy { it.title }
        return MutableLiveData<List<Note>>(sortedNotes)
    }

    override fun getNotesByDate(): LiveData<List<Note>> {
        val sortedNotes = notes.sortedBy { it.date }
        return MutableLiveData<List<Note>>(sortedNotes)
    }

    private fun refreshLiveData() {
        observableNotes.value = notes.toList()  // Use setValue instead of postValue
    }
}
