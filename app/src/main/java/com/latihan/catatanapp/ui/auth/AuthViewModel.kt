package com.latihan.catatanapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.latihan.catatanapp.data.local.note.User
import com.latihan.catatanapp.repository.NoteRepository
import com.latihan.catatanapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel untuk menangani logika otentikasi pengguna.
 *
 * @property repository Repository yang digunakan untuk berinteraksi dengan data pengguna.
 */
class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    // LiveData untuk menyimpan data pengguna yang sedang login
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    /**
     * Mendaftarkan pengguna baru.
     *
     * @param user Data pengguna yang akan didaftarkan.
     * @return LiveData yang berisi hasil operasi pendaftaran.
     */
    fun registerUser(user: User) = liveData(Dispatchers.IO) {
        val result = repository.insertUser(user)
        emit(result)
    }

    /**
     * Melakukan login pengguna.
     *
     * @param username Username pengguna.
     * @param password Password pengguna.
     * @return LiveData yang berisi data pengguna jika login berhasil, null jika gagal.
     */
    fun loginUser(username: String, password: String) = liveData(Dispatchers.IO) {
        val user = repository.getUser(username, password)
        emit(user)
    }
}

