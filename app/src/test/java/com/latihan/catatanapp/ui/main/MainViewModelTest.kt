package com.latihan.catatanapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.repository.NoteRepository
import com.latihan.catatanapp.utils.DataDummy
import com.latihan.catatanapp.utils.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Kelas pengujian untuk MainViewModel.
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var noteRepository: NoteRepository
    private lateinit var mainViewModel: MainViewModel
    private val dummyNote = DataDummy.generateDummyNewsEntity()

    /**
     * Metode setup yang dijalankan sebelum setiap pengujian.
     *
     * Inisialisasi MainViewModel dengan mock dari NoteRepository.
     */
    @Before
    fun setUp() {
        mainViewModel = MainViewModel(noteRepository)
    }

    /**
     * Menguji bahwa metode `getAllNotes` pada `mainViewModel` mengembalikan nilai yang benar dan berhasil.
     */
    @Test
    fun `when Get AllNotes by Return Success`() {
        val expectedNotes = MutableLiveData<List<Note>>()
        expectedNotes.value = dummyNote
        `when`(mainViewModel.getAllNotes()).thenReturn(expectedNotes)

        val actualNote = mainViewModel.getAllNotes().getOrAwaitValue()
        Mockito.verify(noteRepository).getAllNotes()
        assertNotNull(actualNote)
    }

    /**
     * Menguji bahwa metode `getSortedNotesByTitle` pada `mainViewModel` mengembalikan nilai yang benar dan berhasil.
     */
    @Test
    fun `when get Notes ordered by title and Return Success` () {
        val notesByTitle = MutableLiveData<List<Note>>()
        notesByTitle.value = dummyNote
        `when`(mainViewModel.getSortedNotesByTitle()).thenReturn(notesByTitle)

        val actualNote = mainViewModel.getSortedNotesByTitle().getOrAwaitValue()
        Mockito.verify(noteRepository).getNotesByTitle()
        assertNotNull(actualNote)
    }

    /**
     * Menguji bahwa metode `getNoteByDate` pada `mainViewModel` mengembalikan nilai yang benar dan berhasil.
     */
    @Test
    fun `when get Notes ordered by date and return Success`() {
        val notesByDate = MutableLiveData<List<Note>>()
        notesByDate.value = dummyNote
        `when`(mainViewModel.getNoteByDate()).thenReturn(notesByDate)

        val actualNote = mainViewModel.getNoteByDate().getOrAwaitValue()
        Mockito.verify(noteRepository).getNotesByDate()
        assertNotNull(actualNote)
    }
}
