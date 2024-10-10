package com.example.appformulas

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Triangulo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_triangulo)

        val txtAreaTriangulo = findViewById<TextView>(R.id.txtAreaTriangulo)

        val bundle:Bundle ?= intent.extras
        val base:Double = bundle!!.getDouble("Base")
        val altura:Double = bundle!!.getDouble("Altura")

        val operacion:Double = (base * altura) / 2
        txtAreaTriangulo.text = operacion.toString()
    }
}