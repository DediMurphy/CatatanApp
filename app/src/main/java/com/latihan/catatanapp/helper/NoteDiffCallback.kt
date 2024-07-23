package com.latihan.catatanapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.latihan.catatanapp.data.local.Note

/**
 * **NoteDiffCallback**
 *
 * Kelas ini adalah implementasi dari `DiffUtil.Callback` yang digunakan untuk membandingkan
 * dua list dari `Note` untuk keperluan pembaruan efisien pada `RecyclerView`.
 * Kelas ini membantu `RecyclerView` untuk menentukan item mana yang telah berubah
 * sehingga hanya bagian yang diubah yang diperbarui, bukan seluruh list.
 *
 * @param oldNoteList Daftar catatan lama yang akan dibandingkan.
 * @param newNoteList Daftar catatan baru yang akan dibandingkan dengan daftar lama.
 */
class NoteDiffCallback(private val oldNoteList: List<Note>, private val newNoteList: List<Note>) : DiffUtil.Callback() {
    /**
     * **NoteDiffCallback**
     *
     * Kelas ini adalah implementasi dari `DiffUtil.Callback` yang digunakan untuk membandingkan
     * dua list dari `Note` untuk keperluan pembaruan efisien pada `RecyclerView`.
     * Kelas ini membantu `RecyclerView` untuk menentukan item mana yang telah berubah
     * sehingga hanya bagian yang diubah yang diperbarui, bukan seluruh list.
     *
     * @param oldNoteList Daftar catatan lama yang akan dibandingkan.
     * @param newNoteList Daftar catatan baru yang akan dibandingkan dengan daftar lama.
     */
    override fun getOldListSize(): Int = oldNoteList.size
    override fun getNewListSize(): Int = newNoteList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldNoteList[oldItemPosition]
        val newNote = newNoteList[newItemPosition]
        return oldNote.title == newNote.title && oldNote.description == newNote.description
    }
}