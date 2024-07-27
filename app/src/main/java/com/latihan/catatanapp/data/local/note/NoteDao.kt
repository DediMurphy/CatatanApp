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
     * Menyisipkan pengguna baru ke dalam database.
     *
     * @param user Pengguna yang akan disisipkan ke dalam database.
     * @return ID dari pengguna yang baru disisipkan.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    /**
     * Mengambil pengguna dari database berdasarkan username dan password.
     *
     * @param username Username dari pengguna yang akan diambil.
     * @param password Password dari pengguna yang akan diambil.
     * @return Pengguna yang cocok dengan username dan password yang diberikan, atau null jika tidak ditemukan.
     */
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?

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

    @Query("SELECT * FROM note WHERE title LIKE :query ORDER BY title ASC")
    fun searchNotesByTitle(query: String): LiveData<List<Note>>

    /**
     * Mengambil data berdasarkan date(tanggal) di database
     * @return A LiveData list of all notes ordered date.
     */
    @Query("SELECT * FROM note ORDER BY date ASC")
    fun getNotesByDate(): LiveData<List<Note>>
}