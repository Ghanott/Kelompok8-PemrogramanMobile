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
        val editTextEmail: EditText = findViewById<EditText>(R.id.etEmail)
        val editTextPassword = findViewById<EditText>(R.id.etPassword)

        buttonLogin.setOnClickListener {
            val email: String = editTextEmail.text.toString()

            val intentLogin = Intent(this, MainActivity::class.java)
            intentLogin.putExtra(KEY_EMAIL, email)
            startActivity(intentLogin)

            Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
        }

        }
    companion object {
        const val KEY_EMAIL = "email"
    }
    }
