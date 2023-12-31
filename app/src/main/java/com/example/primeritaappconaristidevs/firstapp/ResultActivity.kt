package com.example.primeritaappconaristidevs.firstapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.primeritaappconaristidevs.R

class ResultActivity : AppCompatActivity() {

    private lateinit var btnVolver: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text = "Hola $name"

        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener{ onBackPressed() }
    }
}