package com.latihan.catatanapp.ui.noteup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.latihan.catatanapp.R
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.databinding.ActivityNoteAddUpdateBinding
import com.latihan.catatanapp.helper.DateHelper
import com.latihan.catatanapp.ui.ViewModelFactory

/**
 * Kelas aktivitas untuk menambah atau mengupdate catatan.
 */
@Suppress("DEPRECATION")
class NoteAddUpdateActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    // Indikator apakah catatan sedang di edit
    private var isEdit = false
    // Catatan yang sedang di edit atau ditambah
    private var note: Note? = null

    // ViewModel untuk menambah atau mengupdate catatan
    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel
    private var _activityNoteAddUpdateBinding: ActivityNoteAddUpdateBinding? = null
    private val binding get() = _activityNoteAddUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginisialisasi binding dan menyiapkan tampilan
        _activityNoteAddUpdateBinding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        noteAddUpdateViewModel = obtainViewModel(this@NoteAddUpdateActivity)

        // Mendapatkan catatan dari intent
        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            isEdit = true
        } else {
            note = Note()
        }
        val actionBarTitle: String
        val btnTitle: String
        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (note != null) {
                note?.let { note ->
                    binding?.edtTitle?.setText(note.title)
                    binding?.edtDescription?.setText(note.description)
                    binding?.tvDate?.setText(note.date)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.btnSubmit?.text = btnTitle

        // Menambahkan listener untuk tombol submit
        binding?.btnSubmit?.setOnClickListener {
            val title = binding?.edtTitle?.text.toString().trim()
            val description = binding?.edtDescription?.text.toString().trim()
            when {
                title.isEmpty() -> {
                    binding?.edtTitle?.error = getString(R.string.empty)
                }
                description.isEmpty() -> {
                    binding?.edtDescription?.error = getString(R.string.empty)
                }
                else -> {
                    note.let { note ->
                        note?.title = title
                        note?.description = description
                    }
                    if (isEdit) {
                        noteAddUpdateViewModel.update(note as Note)
                        showToast(getString(R.string.changed))
                    } else {
                        note.let { note ->
                            note?.date = DateHelper.getCurrentDate()
                        }
                        noteAddUpdateViewModel.insert(note as Note)
                        showToast(getString(R.string.added))
                    }
                    finish()
                }
            }
        }
        // Menambahkan callback untuk tombol back
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAlertDialog(ALERT_DIALOG_CLOSE)
            }
        })

        binding?.topAppBarNoteEdt?.setNavigationOnClickListener {
            showAlertDialog(ALERT_DIALOG_CLOSE)
        }

        onMenu()
    }

    private fun onMenu() {
        binding?.topAppBarNoteEdt?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_delete -> {
                    showAlertDialog(ALERT_DIALOG_DELETE)
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Menampilkan dialog konfirmasi.
     *
     * @param type Jenis dialog yang akan ditampilkan.
     */
    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    noteAddUpdateViewModel.delete(note as Note)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityNoteAddUpdateBinding = null
    }

    /**
     * Mendapatkan instance ViewModel.
     *
     * @param activity Aktivitas saat ini.
     * @return Instance NoteAddUpdateViewModel.
     */
    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[NoteAddUpdateViewModel::class.java]
    }

    /**
     * Menampilkan pesan toast.
     *
     * @param message Pesan yang akan ditampilkan.
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}