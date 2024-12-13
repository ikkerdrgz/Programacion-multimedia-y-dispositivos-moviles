package com.example.appcalculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appcalculadora.ui.theme.AppCalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCalculadoraTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = MaterialTheme.colorScheme.primary)
    {
        var entrada1 by remember { mutableStateOf("")}
        var entrada2 by remember { mutableStateOf("")}
        var resultado by remember { mutableStateOf(0.0)}
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text("Introduce el numero 1")
                TextField(
                    value = entrada1,
                    onValueChange = {entrada1 = it},
                    label = { Text("N1")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text("Introduce el numero 2")
                TextField(
                    value = entrada2,
                    onValueChange = {entrada2 = it},
                    label = { Text("N2")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Button(modifier = Modifier.padding(5.dp),
                    onClick = {resultado = entrada1.toDouble() + entrada2.toDouble()},
                    colors = ButtonDefaults.buttonColors(Color.Red)) {
                    Text("Sumar")
                }
                Button(modifier = Modifier.padding(5.dp),
                    onClick = {resultado = entrada1.toDouble() - entrada2.toDouble()},
                    colors = ButtonDefaults.buttonColors(Color.Red)) {
                    Text("Restar")
                }
            }
            Text(text = "Resultado ${resultado}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreeliminar() {
    AppCalculadoraTheme {
        MiApp()
    }
}