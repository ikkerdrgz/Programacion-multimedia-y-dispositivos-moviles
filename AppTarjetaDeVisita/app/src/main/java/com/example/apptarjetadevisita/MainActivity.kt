package com.example.apptarjetadevisita

import android.media.midi.MidiDeviceInfo.PortInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.apptarjetadevisita.ui.theme.AppTarjetaDeVisitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTarjetaDeVisitaTheme {
                Surface (color = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary) {
                        crearTarjetaVisita()
                    }
            }
        }
    }
}

@Composable
fun crearTarjetaVisita() {
    val estadoDelBoton = remember {
        mutableStateOf(false)
    }
    Surface (modifier = Modifier.fillMaxWidth().fillMaxSize()) {
        Card (modifier = Modifier.width(200.dp)
            .height(390.dp)
            .padding(30.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.Yellow,
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        )
        {
            Column (modifier = Modifier.height(300.dp) .width(500.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                fotoPerfil()
                HorizontalDivider(thickness = 5.dp)
                infoPerfil()
                Button(
                    onClick = {
                        estadoDelBoton.value = !estadoDelBoton.value
                    }
                ) {
                    Text("Portafolio", style = MaterialTheme.typography.labelLarge)
                }
            }
            if (estadoDelBoton.value){
                Contenido()
            }else{
                Box(){

                }
            }
        }
    }
}

@Composable
private fun infoPerfil() {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iker Rodriguez",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Programador Android",
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@developGuay",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun fotoPerfil(modifier: Modifier=Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.foto),
            contentDescription = "Imagen del perfil",
            modifier = Modifier.size(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTarjetaDeVisitaTheme {
        crearTarjetaVisita()
    }
}

@Preview
@Composable
fun Contenido() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize().padding(5.dp)){
        Surface (modifier = Modifier.padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
                shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.Gray)
        ){
            Portfolio(data = listOf("Proyecto 1", "Proyecto 2", "Proyecto 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){ item->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.inverseSurface
                )
            )
            {
                Row (modifier = Modifier.padding(8.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp))
                {
                    fotoPerfil(modifier = Modifier.size(100.dp))
                }
            }
        }
    }
}
