package com.latihan.catatanapp.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.latihan.catatanapp.R
import com.latihan.catatanapp.data.local.note.User
import com.latihan.catatanapp.databinding.ActivityLoginBinding
import com.latihan.catatanapp.databinding.ActivityRegisterBinding
import com.latihan.catatanapp.ui.ViewModelFactory
import com.latihan.catatanapp.ui.auth.AuthViewModel
import com.latihan.catatanapp.ui.auth.login.LoginActivity

/**
 * Kelas RegisterActivity bertanggung jawab untuk menangani tampilan dan logika register pengguna.
 * Kelas ini menggunakan View Binding dan ViewModel untuk memisahkan tampilan dan logika.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(AuthViewModel::class.java)

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEdittext.text.toString()
            val email = binding.emailEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            val user = User(username = username, password = password, email = email)
            viewModel.registerUser(user).observe(this, Observer { result ->
                if (result > 0) {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
