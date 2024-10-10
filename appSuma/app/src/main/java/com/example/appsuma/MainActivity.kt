package com.example.appsuma

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtN1 = findViewById<EditText>(R.id.edtN1)
        val edtN2 = findViewById<EditText>(R.id.edtN2)
        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMultiplicacion = findViewById<Button>(R.id.btnMultiplicacion)
        val btnDivision = findViewById<Button>(R.id.btnDivision)
        val textResultado = findViewById<TextView>(R.id.textResultado)

        btnSuma.setOnClickListener {
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1 + n2
            textResultado.text = "Resultado: ${res.toString()}"
        }

        btnResta.setOnClickListener {
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1 - n2
            textResultado.text = "Resultado: ${res.toString()}"
        }

        btnMultiplicacion.setOnClickListener {
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1 * n2
            textResultado.text = "Resultado: ${res.toString()}"
        }

        btnDivision.setOnClickListener {
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1 / n2
            textResultado.text = "Resultado: ${res.toString()}"
        }
    }
}