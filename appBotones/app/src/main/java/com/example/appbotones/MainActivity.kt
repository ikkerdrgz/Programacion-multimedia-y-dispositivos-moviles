package com.example.appbotones

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txtApagado = findViewById<TextView>(R.id.txtApagado)
        val txtSwitch = findViewById<TextView>(R.id.txtSwitch)
        val btnBluetooth = findViewById<Button>(R.id.btnBluetooth)
        val btnApagado = findViewById<ToggleButton>(R.id.tgbApagado)
        val btnSwitch = findViewById<Switch>(R.id.btnSwitch)
        val imageButton = findViewById<ImageButton>(R.id.imbReiniciar)
        val opcion1 = findViewById<CheckBox>(R.id.opcion1)
        val opcion2 = findViewById<CheckBox>(R.id.opcion2)
        val opcion3 = findViewById<CheckBox>(R.id.opcion3)

    }
}