package com.example.appcompartirdatos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtMensaje = findViewById<EditText>(R.id.edtMensaje)

        val btnDirecto = findViewById<Button>(R.id.btnDirecto)
        btnDirecto.setOnClickListener {
            val mensaje : String = edtMensaje.text.toString()
            //Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            val intent = Intent(this,Activity2::class.java)
            intent.putExtra("Envio",mensaje)
            startActivity(intent)
        }

        val btnElige = findViewById<Button>(R.id.btnElige)
        btnElige.setOnClickListener {
            val mensaje : String = edtMensaje.text.toString()
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            val intent = Intent()
            intent.action = Intent.ACTION_SEND

            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,mensaje)
            startActivity(Intent.createChooser(intent,"Share to"))


        }
    }
}