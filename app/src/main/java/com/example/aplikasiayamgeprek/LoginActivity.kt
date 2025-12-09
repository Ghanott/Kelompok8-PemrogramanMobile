package com.example.aplikasiayamgeprek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikasiayamgeprek.home.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        val editTextEmail: EditText = findViewById<EditText>(R.id.etUsername)
        val editTextPassword = findViewById<EditText>(R.id.etPassword)

        buttonLogin.setOnClickListener {
            val username: String = editTextEmail.text.toString()
            val password: String = editTextPassword.text.toString()
            if (username.isEmpty()) {
                editTextEmail.error = "Email tidak boleh kosong"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                editTextPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener

            }



            val intentLogin = Intent(this, MainActivity::class.java)
            intentLogin.putExtra(KEY_USERNAME, username)
            startActivity(intentLogin)

            Toast.makeText(this, "Selamat Datang $username", Toast.LENGTH_SHORT).show()
        }

        }
    companion object {
        const val KEY_USERNAME = "KEY_USERNAME"
    }
    }
