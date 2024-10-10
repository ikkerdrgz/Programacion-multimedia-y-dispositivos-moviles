package com.example.appformulas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtLado = findViewById<EditText>(R.id.edtLadoCuadrado)
        val edtBase = findViewById<EditText>(R.id.edtBaseTriangulo)
        val edtAltura = findViewById<EditText>(R.id.edtAlturaTriangulo)
        val edtRadio = findViewById<EditText>(R.id.edtRadioCirculo)

        val btnCuadrado = findViewById<Button>(R.id.btnCuadrado)
        val btnTriangulo = findViewById<Button>(R.id.btnTriangulo)
        val btnCirculo = findViewById<Button>(R.id.btnCirculo)

        btnCuadrado.setOnClickListener {
            val lado : Double = edtLado.text.toString().toDouble()
            
            val intent = Intent(this,Cuadrado::class.java)
            intent.putExtra("Lado",lado)
            startActivity(intent)
        }

        btnTriangulo.setOnClickListener {
            val base : Double = edtBase.text.toString().toDouble()
            val altura : Double = edtAltura.text.toString().toDouble()

            val intent = Intent(this,Triangulo::class.java)
            intent.putExtra("Base",base)
            intent.putExtra("Altura",altura)
            startActivity(intent)
        }

        btnCirculo.setOnClickListener {
            val radio : Double = edtRadio.text.toString().toDouble()
            val intent = Intent(this,Circulo::class.java)
            intent.putExtra("Radio",radio)
            startActivity(intent)
        }
    }
}