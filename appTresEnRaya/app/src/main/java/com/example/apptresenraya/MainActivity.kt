package com.example.apptresenraya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptresenraya.ui.theme.AppTresEnRayaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTresEnRayaTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    val posT11 = remember { mutableStateOf("") }
    val posT12 = remember { mutableStateOf("") }
    val posT13 = remember { mutableStateOf("") }
    val posT21 = remember { mutableStateOf("") }
    val posT22 = remember { mutableStateOf("") }
    val posT23 = remember { mutableStateOf("") }
    val posT31 = remember { mutableStateOf("") }
    val posT32 = remember { mutableStateOf("") }
    val posT33 = remember { mutableStateOf("") }
    val turno = remember { mutableStateOf(0) }
    val resultado = remember { mutableStateOf("") }

    fun verificarGanador(): String {
        if (posT11.value == "X" && posT12.value == "X" && posT13.value == "X") return "X"
        if (posT21.value == "X" && posT22.value == "X" && posT23.value == "X") return "X"
        if (posT31.value == "X" && posT32.value == "X" && posT33.value == "X") return "X"

        if (posT11.value == "X" && posT21.value == "X" && posT31.value == "X") return "X"
        if (posT12.value == "X" && posT22.value == "X" && posT32.value == "X") return "X"
        if (posT13.value == "X" && posT23.value == "X" && posT33.value == "X") return "X"

        if (posT11.value == "X" && posT22.value == "X" && posT33.value == "X") return "X"
        if (posT13.value == "X" && posT22.value == "X" && posT31.value == "X") return "X"

        if (posT11.value == "O" && posT12.value == "O" && posT13.value == "O") return "O"
        if (posT21.value == "O" && posT22.value == "O" && posT23.value == "O") return "O"
        if (posT31.value == "O" && posT32.value == "O" && posT33.value == "O") return "O"

        if (posT11.value == "O" && posT21.value == "O" && posT31.value == "O") return "O"
        if (posT12.value == "O" && posT22.value == "O" && posT32.value == "O") return "O"
        if (posT13.value == "O" && posT23.value == "O" && posT33.value == "O") return "O"

        if (posT11.value == "O" && posT22.value == "O" && posT33.value == "O") return "O"
        if (posT13.value == "O" && posT22.value == "O" && posT31.value == "O") return "O"

        return ""
    }

    fun hacerJugada(i: Int, j: Int) {
        if (verificarGanador().isEmpty()) {
            val pos = when {
                i == 1 && j == 1 -> posT11
                i == 1 && j == 2 -> posT12
                i == 1 && j == 3 -> posT13
                i == 2 && j == 1 -> posT21
                i == 2 && j == 2 -> posT22
                i == 2 && j == 3 -> posT23
                i == 3 && j == 1 -> posT31
                i == 3 && j == 2 -> posT32
                i == 3 && j == 3 -> posT33
                else -> null
            }

            pos?.value = if (turno.value % 2 == 0) "X" else "O"
            turno.value++

            if (verificarGanador().equals("X")) {
                resultado.value = "Ganador: X"
            }
            if (verificarGanador().equals("O")) {
                resultado.value = "Ganador: O"
            }
        }
    }

    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(), color = MaterialTheme.colorScheme.background) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Row {
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(1, 1) })
                {
                    Text(posT11.value,fontSize = 20.sp)
                }
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(1, 2) })
                {
                    Text(posT12.value,fontSize = 20.sp)
                }
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(1, 3) })
                {
                    Text(posT13.value,fontSize = 20.sp)
                }
            }

            Row {
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(2, 1) })
                {
                    Text(posT21.value,fontSize = 20.sp)
                }
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(2, 2) })
                {
                    Text(posT22.value,fontSize = 20.sp)
                }
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(2, 3) })
                {
                    Text(posT23.value,fontSize = 20.sp)
                }
            }

            Row {
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(3, 1) })
                {
                    Text(posT31.value,fontSize = 20.sp)
                }
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(3, 2) })
                {
                    Text(posT32.value,fontSize = 20.sp)
                }
                Button(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .height(60.dp),
                    onClick = { hacerJugada(3, 3) })
                {
                    Text(posT33.value,fontSize = 20.sp)
                }
            }

            if (resultado.value.isNotEmpty()) {
                Text(fontSize = 20.sp, text = resultado.value, modifier = Modifier.padding(16.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTresEnRayaTheme {
        MiApp()
    }
}