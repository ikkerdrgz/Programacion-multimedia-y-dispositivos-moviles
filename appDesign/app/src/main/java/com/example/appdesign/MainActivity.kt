package com.example.appdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdesign.ui.theme.AppDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDesignTheme {
                MiApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MiApp() {
    Scaffold(
        topBar = { TopAppBar(title = {Text("Barra Superior")},
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
                scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.primary
            )
        )
        },
        bottomBar = { BottomAppBar() { Text("Barra Inferior")} },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Check, contentDescription = "Check")
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)){
                Surface(modifier = Modifier
                    .fillMaxSize(),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = 10.dp,
                    border = BorderStroke(3.dp, Color.Black)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "¿Como estan ustedes",
                            fontSize = 45.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 50.sp
                        )
                        Icon(Icons.Filled.Star, contentDescription = "Star",
                            modifier = Modifier.width(50.dp).height(50.dp))

                        TextField("Introduce un valor", onValueChange = {})
                    }
                    /*
                    Column(modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Hola a todos")
                        Text("¿Como estan ustedes?")
                    }
                    Row(modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text("Hola a todos")
                        Text("¿Como estan ustedes?")
                    }
                    Row(modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text("Payasos a la tele")
                        Column(modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Hola a todos")
                            Text("¿Como estan ustedes?")
                        }
                        Box(){
                            Text("      de la tele")
                            Icon(Icons.Filled.Check, contentDescription = "Check")
                        }
                    }*/
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppDesignTheme {
        MiApp()
    }
}