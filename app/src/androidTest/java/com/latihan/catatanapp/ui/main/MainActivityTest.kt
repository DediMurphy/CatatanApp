package com.latihan.catatanapp.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.latihan.catatanapp.R

/**
 * Kelas uji untuk menguji MainActivity menggunakan framework pengujian UI Android.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    /**
     * Menyiapkan lingkungan uji dengan meluncurkan MainActivity sebelum setiap metode uji.
     */
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    /**
     * Menguji fungsionalitas utama dari MainActivity.
     *
     * - Memeriksa apakah RecyclerView dengan ID `rv_notes` ditampilkan.
     * - Melakukan aksi klik pada RecyclerView.
     * - Memeriksa apakah Floating Action Button (FAB) dengan ID `fab_add` ditampilkan.
     * - Melakukan aksi klik pada FAB.
     */

    @Test
    fun loadListIsDisplay() {
        onView(withId(R.id.rv_notes)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_notes)).perform(click())
    }
    fun loadViewFab() {
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())
    }
}
