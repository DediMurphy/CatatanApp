package com.latihan.catatanapp.ui.noteup

import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.latihan.catatanapp.R

/**
 * **NoteAddUpdateActivityTest**
 *
 * Kelas ini adalah pengujian UI untuk `NoteAddUpdateActivity`. Pengujian ini bertujuan untuk memverifikasi
 * bahwa elemen-elemen UI pada aktivitas tersebut berfungsi sebagaimana mestinya. Kelas ini menggunakan
 * JUnit4 untuk menjalankan pengujian.
 *
 * @see NoteAddUpdateActivity
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class NoteAddUpdateActivityTest() {

    // Data dummy untuk digunakan dalam pengujian input
    private val title = "UI Testing"
    private val description = "Ini merupakan UI tes"

    /**
     * **setUp**
     *
     * Metode ini dijalankan sebelum setiap metode uji untuk mempersiapkan lingkungan pengujian.
     * Dalam hal ini, metode ini meluncurkan `NoteAddUpdateActivity` sebelum setiap pengujian dijalankan.
     */
    @Before
    fun setUp() {
        ActivityScenario.launch(NoteAddUpdateActivity::class.java)
    }

    /**
     * **uiTestInputandButton**
     *
     * Metode uji ini memverifikasi fungsi input dan tombol di `NoteAddUpdateActivity`.
     * Pengujian ini mencakup:
     * - Memasukkan teks ke dalam kolom judul (`edt_title`).
     * - Memasukkan teks ke dalam kolom deskripsi (`edt_description`).
     * - Memeriksa apakah tombol kirim (`btn_submit`) ditampilkan.
     * - Mengklik tombol kirim (`btn_submit`).
     */
    @Test
    fun uiTestInputandButtun() {
        onView(withId(R.id.edt_title)).perform(typeText(title), closeSoftKeyboard())
        onView(withId(R.id.edt_description)).perform(typeText(description), closeSoftKeyboard())

        onView(withId(R.id.btn_submit)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_submit)).perform(click())
    }
}