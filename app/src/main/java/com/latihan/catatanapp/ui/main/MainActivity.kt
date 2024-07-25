package com.latihan.catatanapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.catatanapp.R
import com.latihan.catatanapp.databinding.ActivityMainBinding
import com.latihan.catatanapp.ui.ViewModelFactory
import com.latihan.catatanapp.ui.noteup.NoteAddUpdateActivity

/**
 * Kelas aktivitas utama yang mengelola tampilan utama aplikasi.
 */
class MainActivity : AppCompatActivity() {

    // Binding untuk aktivitas utama
    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    // Adapter untuk menampilkan catatan
    private lateinit var adapter: NoteAdapter
    // ViewModel untuk mengakses data catatan
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginisialisasi binding dan menyiapkan tampilan
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Mendapatkan ViewModel
        mainViewModel = obtainViewModel(this@MainActivity)

        // Menginisialisasi adapter
        adapter = NoteAdapter()

        // Mengatur RecyclerView
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

        onMenu()

        // Menambahkan listener untuk tombol tambah
        binding?.fabAdd?.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
            startActivity(intent)
        }

        // Mengamati data catatan dan memperbarui adapter
        mainViewModel.getAllNotes().observe(this) { noteList ->
            adapter.setListNotes(noteList)
        }
    }

    private fun onMenu() {
        binding?.topAppBar?.setOnMenuItemClickListener {menuItem ->
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

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}