package com.latihan.catatanapp.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.latihan.catatanapp.data.local.pref.SharedPrefManager
import com.latihan.catatanapp.databinding.ActivityLoginBinding
import com.latihan.catatanapp.ui.ViewModelFactory
import com.latihan.catatanapp.ui.auth.AuthViewModel
import com.latihan.catatanapp.ui.auth.register.RegisterActivity
import com.latihan.catatanapp.ui.main.MainActivity

/**
 * Kelas LoginActivity bertanggung jawab untuk menangani tampilan dan logika login pengguna.
 * Kelas ini menggunakan View Binding dan ViewModel untuk memisahkan tampilan dan logika.
 */
class LoginActivity : AppCompatActivity() {

    // Binding untuk mengakses tampilan di layout
    private lateinit var binding: ActivityLoginBinding

    // ViewModel untuk menangani logika otentikasi
    private lateinit var viewModel: AuthViewModel

    /**
     * Metode onCreate dipanggil saat aktivitas dibuat.
     * Inisialisasi binding, ViewModel, dan pengaturan listener untuk tombol.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi ViewModel dengan menggunakan ViewModelFactory
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(AuthViewModel::class.java)

        // Listener untuk membuka aktivitas registrasi
        binding.openRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Listener untuk tombol login
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            // Memanggil metode login di ViewModel dan mengamati hasilnya
            viewModel.loginUser(username, password).observe(this, Observer { user ->
                if (user != null) {
                    // Login berhasil
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    SharedPrefManager.setLoginStatus(this, true)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Login gagal
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
