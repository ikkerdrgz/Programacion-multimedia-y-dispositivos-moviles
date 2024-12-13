package com.example.appecuacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberTopAppBarState
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
import com.example.appecuacion.ui.theme.AppEcuacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppEcuacionTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = MaterialTheme.colorScheme.primary)
    {
        var entradaA by remember { mutableStateOf("") }
        var entradaB by remember { mutableStateOf("") }
        var entradaC by remember { mutableStateOf("") }
        var resultado1 by remember { mutableStateOf(0.0) }
        var resultado2 by remember { mutableStateOf(0.0) }
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Row () {
                TextField(
                    modifier = Modifier.width(55.dp).height(55.dp),
                    value = entradaA,
                    onValueChange = {entradaA = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text("x² + ")
                TextField(
                    modifier = Modifier.width(55.dp).height(55.dp),
                    value = entradaB,
                    onValueChange = {entradaB = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text("x + ")
                TextField(
                        modifier = Modifier.width(55.dp).height(55.dp),
                value = entradaC,
                onValueChange = {entradaC = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(" = 0")
            }

            Row (modifier = Modifier.padding(top = 10.dp)){
                Button(modifier = Modifier.padding(5.dp),
                    onClick = {resultado1 = ((entradaB.toDouble()*(-1)) - Math.sqrt(Math.pow(entradaB.toDouble(),entradaB.toDouble()) - (4*entradaA.toDouble()*entradaC.toDouble())) / (2*entradaA.toDouble()))},
                    colors = ButtonDefaults.buttonColors(Color.Red)) {
                        Text("Calcular")
                }
            }

            Row {
                Text(text = "x¹ ${resultado1}")
            }
            Text(text = "x² ${resultado2}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppEcuacionTheme {
        MiApp()
    }
}