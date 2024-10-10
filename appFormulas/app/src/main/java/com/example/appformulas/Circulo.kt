package com.example.appformulas

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Circulo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_circulo)

        val bundle:Bundle ?= intent.extras
        val radio:Double = bundle!!.getDouble("Radio")

        val txtAreaCirculo = findViewById<TextView>(R.id.txtAreaCirculo)
        val operacion:Double = Math.PI * (radio * radio)
        txtAreaCirculo.text = operacion.toString()
    }
}