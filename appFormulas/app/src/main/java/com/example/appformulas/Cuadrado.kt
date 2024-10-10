package com.example.appformulas

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Cuadrado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuadrado)

        val bundle:Bundle ?= intent.extras
        val lado:Double = bundle!!.getDouble("Lado")

        val txtAreaCuadrado = findViewById<TextView>(R.id.txtAreaCuadrado)
        val operacion:Double = lado * lado
        txtAreaCuadrado.text = operacion.toString()
    }
}