package com.example.aplikasiayamgeprek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikasiayamgeprek.home.HomeFragment
import com.example.aplikasiayamgeprek.home.MainActivity

class Pembayaran_Berhasil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pembayaran_berhasil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnKembali = findViewById<Button>(R.id.btnContinue)

        btnKembali.setOnClickListener {
            goToHome()

        }
    }

    private fun goToHome() {
        val intentGoToHome = Intent(this, MainActivity::class.java)

        intentGoToHome.putExtra("TARGET_FRAGMENT", R.id.homeFragment)
        startActivity(intentGoToHome)
        finish()
    }

    override fun onBackPressed() {
        goToHome()
    }
}