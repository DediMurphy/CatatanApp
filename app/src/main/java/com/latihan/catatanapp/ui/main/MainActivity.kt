package com.latihan.catatanapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.catatanapp.R
import com.latihan.catatanapp.data.local.pref.SharedPrefManager
import com.latihan.catatanapp.databinding.ActivityLoginBinding
import com.latihan.catatanapp.databinding.ActivityMainBinding
import com.latihan.catatanapp.ui.ViewModelFactory
import com.latihan.catatanapp.ui.auth.login.LoginActivity
import com.latihan.catatanapp.ui.noteup.NoteAddUpdateActivity

/**
 * Kelas aktivitas utama yang mengelola tampilan utama aplikasi.
 */
class MainActivity : AppCompatActivity() {

    // Binding untuk aktivitas utama
    private lateinit var binding: ActivityMainBinding

    // Adapter untuk menampilkan catatan
    private lateinit var adapter: NoteAdapter
    // ViewModel untuk mengakses data catatan
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        if (!SharedPrefManager.isLoggedIn(this)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        // Menginisialisasi binding dan menyiapkan tampilan

        // Mendapatkan ViewModel
        mainViewModel = obtainViewModel(this@MainActivity)

        // Menginisialisasi adapter
        adapter = NoteAdapter()

        // Mengatur RecyclerView
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.setHasFixedSize(true)
        binding.rvNotes.adapter = adapter

        onMenu()

        // Menambahkan listener untuk tombol tambah
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
            startActivity(intent)
        }

        // Mengamati data catatan dan memperbarui adapter
        mainViewModel.getAllNotes().observe(this) { noteList ->
            adapter.setListNotes(noteList)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                val query = searchView.text.toString()
                mainViewModel.searchNotesByTitle(query).observe(this@MainActivity) { notes ->
                    adapter.setListNotes(notes)
                }
                searchView.hide()
                true
            }
        }
    }

    private fun onMenu() {
        binding.topAppBar.setOnMenuItemClickListener {menuItem ->
            when (menuItem.itemId) {
                R.id.sort_by_id -> {
                    mainViewModel.getAllNotes().observe(this)  { noteList -> adapter.setListNotes(noteList) }
                    true
                }
                R.id.sort_by_title -> {
                    mainViewModel.getSortedNotesByTitle().observe(this) { sortedNotes -> adapter.setListNotes(sortedNotes) }
                    true
                }
                R.id.sort_by_date -> {
                    mainViewModel.getNoteByDate().observe(this) { sortedNotes -> adapter.setListNotes(sortedNotes) }
                    true
                }
                R.id.logout -> {
                    SharedPrefManager.setLoginStatus(this, false)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Mendapatkan instance ViewModel.
     *
     * @param activity Aktivitas saat ini.
     * @return Instance MainViewModel.
     */
     private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("is_logged_in", false)
    }


    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Menutup MainActivity jika perlu
    }

    private fun getCurrentUsername(): String {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }
    private fun updateLoginStatus(isLoggedIn: Boolean) {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("is_logged_in", isLoggedIn)
            apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}