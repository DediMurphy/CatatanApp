package com.latihan.catatanapp.repository

import com.latihan.catatanapp.data.local.note.NoteDao
import com.latihan.catatanapp.data.local.note.User

/**
 * Repository untuk operasi database terkait pengguna (user).
 *
 * @property userDao Data Access Object (DAO) untuk pengguna.
 */
class UserRepository(
    private val userDao: NoteDao,
) {

    /**
     * Menyisipkan pengguna baru ke dalam database.
     *
     * @param user Pengguna yang akan disisipkan.
     * @return ID dari pengguna yang baru disisipkan.
     */
    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    /**
     * Mendapatkan pengguna dari database berdasarkan username dan password.
     *
     * @param username Nama pengguna.
     * @param password Kata sandi pengguna.
     * @return Pengguna yang sesuai atau null jika tidak ditemukan.
     */
    suspend fun getUser(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        /**
         * Mendapatkan instance dari UserRepository.
         * Jika instance belum ada, membuatnya dalam blok yang disinkronkan.
         *
         * @param userDao NoteDao - Data Access Object untuk Note.
         * @return UserRepository - Instance dari UserRepository.
         */
        fun getInstance(
            noteDao: NoteDao,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(noteDao)
            }.also { instance = it }
    }
}

