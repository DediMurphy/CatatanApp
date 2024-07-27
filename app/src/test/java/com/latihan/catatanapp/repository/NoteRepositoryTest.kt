package com.latihan.catatanapp.repository

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.latihan.catatanapp.data.local.note.NoteDao
import com.latihan.catatanapp.utils.DataDummy
import com.latihan.catatanapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

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
        val dummyNotes = DataDummy.generateDummyNewsEntity()[0]
        noteRepository.insert(dummyNotes)
        val allNotes = noteRepository.getAllNotes().getOrAwaitValue()
        Assert.assertTrue(allNotes.contains(dummyNotes))
    }

    /**
     * Menguji bahwa catatan berhasil diupdate/ubah ke repositori ketika metode `update` dipanggil.
     */
    @Test
    fun `when update Should update Note`() {
        val dummyNotes = DataDummy.generateDummyNewsEntity()[0]
        noteRepository.insert(dummyNotes)
        val updatedTitle = "Updated Title"
        val updatedDescription = "Updated Description"
        val updatedNote = dummyNotes.copy(title = updatedTitle, description = updatedDescription)
        val latch = CountDownLatch(1)
        noteRepository.update(updatedNote)
        latch.await(2, TimeUnit.SECONDS)
        val resultNotes = noteRepository.getAllNotes().getOrAwaitValue(2, TimeUnit.SECONDS)
        val resultNote = resultNotes.firstOrNull { it.id == dummyNotes.id }
        assertNotNull(resultNote)
        assertEquals(updatedTitle, resultNote?.title)
        assertEquals(updatedDescription, resultNote?.description)
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