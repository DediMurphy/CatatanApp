package com.latihan.catatanapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.latihan.catatanapp.data.local.note.Note
import com.latihan.catatanapp.databinding.ItemNoteBinding
import com.latihan.catatanapp.helper.NoteDiffCallback
import com.latihan.catatanapp.ui.noteup.NoteAddUpdateActivity

/**
 * Kelas Adapter untuk menampilkan catatan di RecyclerView.
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()

    /**
     * Mengatur daftar catatan yang akan ditampilkan dan menghitung perbedaan untuk pembaruan yang efisien.
     *
     * @param listNotes Daftar catatan yang akan ditampilkan.
     */
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }
    override fun getItemCount(): Int {
        return listNotes.size
    }

    /**
     * Kelas ViewHolder untuk menyimpan dan mengikat tampilan catatan.
     */
    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Mengikat data catatan ke tampilan.
         *
         * @param note Catatan yang akan diikat ke tampilan.
         */
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}