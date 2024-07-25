package com.latihan.catatanapp.data.local.note

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Dao interface for accessing Note data.
 */

@Dao
interface NoteDao {

    /**
     *  Menambahkan catatan(note) baru kedalam database
     *  @param note The note to insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    /**
     * Mengupdate/edit catatan(note)
     * @param note The note to update.
     */
    @Update
    fun update(note: Note)

    /**
     *  Menghapus catatan(note)
     *  @param note The note to delete.
     */
    @Delete
    fun delete(note: Note)

    /**
     * Mengambil data berdasarkan id di database
     * @return A LiveData list of all notes.
     */
    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    /**
     * Mengambil data berdasarkan title(judul) di database
     * @return A LiveData list of all notes ordered title.
     */
    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesByTitle(): LiveData<List<Note>>

    /**
     * Mengambil data berdasarkan date(tanggal) di database
     * @return A LiveData list of all notes ordered date.
     */
    @Query("SELECT * FROM note ORDER BY date ASC")
    fun getNotesByDate(): LiveData<List<Note>>
}