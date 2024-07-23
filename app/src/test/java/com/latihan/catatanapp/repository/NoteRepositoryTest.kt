package com.latihan.catatanapp.repository

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.latihan.catatanapp.data.local.NoteDao
import com.latihan.catatanapp.utils.DataDummy
import com.latihan.catatanapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NoteRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mainLooper: Looper

    private lateinit var noteDao: NoteDao
    private lateinit var noteRepository: NoteRepository

    @Before
    fun setUp() {
        noteDao = FakeNoteDao()
        noteRepository = NoteRepository(noteDao)
        mainLooper = mock(Looper::class.java)
    }

    /**
     * Menguji bahwa catatan berhasil ditambahkan ke repositori ketika metode `insert` dipanggil.
     */
    @Test
    fun `when insertNote Should Add Note`() {
        val dummyNotes = DataDummy.generateDummyNewsEntity()
        val note = dummyNotes[0]
        noteRepository.insert(note)
        val allNotes = noteRepository.getAllNotes().getOrAwaitValue()
        Assert.assertTrue(allNotes.contains(note))
    }

    /**
     * Menguji bahwa catatan berhasil dihapus dari repositori ketika metode `delete` dipanggil.
     */
    @Test
    fun `when deleteNote Should delete Note`() {
        val dummyNote = DataDummy.generateDummyNewsEntity()[0]
        noteRepository.insert(dummyNote)
        noteRepository.delete(dummyNote)
        val allNotes = noteRepository.getAllNotes().getOrAwaitValue()
        Assert.assertFalse(allNotes.contains(dummyNote))
    }
}