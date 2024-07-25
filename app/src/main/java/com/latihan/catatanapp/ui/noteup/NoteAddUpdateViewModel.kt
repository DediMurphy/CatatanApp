package com.latihan.catatanapp.ui.noteup

import androidx.lifecycle.ViewModel
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.repository.NoteRepository

/**
 * Class ViewModel untuk adding, updating, dan deleting notes.
 *
 * @property mNotesRepository The repository for accessing note data.
 */
class NoteAddUpdateViewModel(private val mNotesRepository: NoteRepository): ViewModel() {

    /**
     * Inserts note baru ke repository
     *
     * @param notes The note to insert.
     */
    fun insert(notes: Note) {
        mNotesRepository.insert(notes)
    }

    /**
     * Update note baru ke repository
     *
     * @param notes The note to update.
     */
    fun update(notes: Note) {
        mNotesRepository.update(notes)
    }

    /**
     * Delete note ke repository
     *
     * @param notes The note to delete.
     */
    fun delete(notes: Note) {
        mNotesRepository.delete(notes)
    }
}