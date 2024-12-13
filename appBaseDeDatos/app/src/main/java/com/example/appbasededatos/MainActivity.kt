package com.example.appbasededatos

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appbasededatos.ui.theme.AppBaseDeDatosTheme

private lateinit var ayudanteBaseDatos : AyudanteBaseDatos
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ayudanteBaseDatos = AyudanteBaseDatos(this)
        setContent {
            AppBaseDeDatosTheme {
                MiApp(ayudante = ayudanteBaseDatos)
            }
        }
    }
}

fun insertarUsuario(dbHelper: AyudanteBaseDatos, usuario: Usuario): Long{
    val db = dbHelper.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, usuario.nombre)
        put(AyudanteBaseDatos.COLUMNA_CORREO, usuario.correo)
        put(AyudanteBaseDatos.COLUMNA_EDAD, usuario.edad)
        put(AyudanteBaseDatos.COLUMNA_TELEFONO, usuario.telefono)
    }
    return db.insert(AyudanteBaseDatos.TABLA_VERSION,
        null, valores)
}

fun eliminarUsuario(ayudante: AyudanteBaseDatos, id: Int): Int {
    val db = ayudante.writableDatabase
    return db.delete(
        AyudanteBaseDatos.TABLA_VERSION,
        "${AyudanteBaseDatos.COLUMNA_ID} = ?",
        arrayOf(id.toString())
    )
}

fun actualizarUsuario(ayudante: AyudanteBaseDatos, usuario: Usuario): Int {
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, usuario.nombre)
        put(AyudanteBaseDatos.COLUMNA_CORREO, usuario.correo)
        put(AyudanteBaseDatos.COLUMNA_EDAD, usuario.edad)
        put(AyudanteBaseDatos.COLUMNA_TELEFONO, usuario.telefono)
    }
    return db.update(
        AyudanteBaseDatos.TABLA_VERSION,
        valores,
        "${AyudanteBaseDatos.COLUMNA_ID} = ?",
        arrayOf(usuario.id.toString())
    )
}

fun obtenerTodosLosUsuarios(ayudante: AyudanteBaseDatos): List<Usuario> {
    val db = ayudante.readableDatabase
    val cursor = db.query(
        AyudanteBaseDatos.TABLA_VERSION,
        arrayOf(
            AyudanteBaseDatos.COLUMNA_ID,
            AyudanteBaseDatos.COLUMNA_NOMBRE,
            AyudanteBaseDatos.COLUMNA_CORREO,
            AyudanteBaseDatos.COLUMNA_EDAD,
            AyudanteBaseDatos.COLUMNA_TELEFONO,
        ),
        null,
        null,
        null,
        null,
        null
    )

    val usuarios = mutableListOf<Usuario>()
    while (cursor.moveToNext()) {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_ID))
        val nombre = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_NOMBRE))
        val correo = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_CORREO))
        val edad = cursor.getInt(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_EDAD))
        val telefono = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_TELEFONO))
        usuarios.add(Usuario(id, nombre, correo, edad, telefono))
    }
    cursor.close()
    return usuarios
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiApp(ayudante: AyudanteBaseDatos) {
    val usuarios = remember { mutableStateListOf<Usuario>() }
    var enModoEdicion by remember { mutableStateOf(false) }
    var usuarioSeleccionado by remember { mutableStateOf(Usuario(0, "", "", 0, "")) }

    LaunchedEffect(Unit) {
        usuarios.addAll(obtenerTodosLosUsuarios(ayudante))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Usuarios") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                PantallaAgregarUsuario(
                    usuario = usuarioSeleccionado,
                    enModoEdicion = enModoEdicion,
                    onUsuarioCambio = { usuarioSeleccionado = it },
                    onGuardar = {
                        if (enModoEdicion) {
                            // Actualizar usuario existente
                            actualizarUsuario(ayudante, usuarioSeleccionado)
                            val index = usuarios.indexOfFirst { it.id == usuarioSeleccionado.id }
                            if (index != -1) {
                                usuarios[index] = usuarioSeleccionado
                            }
                        } else {
                            // Agregar nuevo usuario
                            val id = insertarUsuario(ayudante, usuarioSeleccionado).toInt()
                            usuarios.add(usuarioSeleccionado.copy(id = id))
                        }
                        // Reiniciar el formulario
                        enModoEdicion = false
                        usuarioSeleccionado = Usuario(0, "", "", 0, "")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PantallaListaUsuarios(
                    usuarios = usuarios,
                    onActualizar = { usuario ->
                        // Preparar para editar
                        enModoEdicion = true
                        usuarioSeleccionado = usuario
                    },
                    onEliminar = { usuario ->
                        // Eliminar usuario
                        eliminarUsuario(ayudante, usuario.id)
                        usuarios.remove(usuario)
                    }
                )
            }
        }
    )
}

@Composable
fun PantallaAgregarUsuario(
    usuario: Usuario,
    enModoEdicion: Boolean,
    onUsuarioCambio: (Usuario) -> Unit,
    onGuardar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = usuario.nombre,
            onValueChange = { onUsuarioCambio(usuario.copy(nombre = it)) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = usuario.correo,
            onValueChange = { onUsuarioCambio(usuario.copy(correo = it)) },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = usuario.edad.toString(),
            onValueChange = { onUsuarioCambio(usuario.copy(edad = it.toIntOrNull() ?: 0)) },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = usuario.telefono,
            onValueChange = { onUsuarioCambio(usuario.copy(telefono = it)) },
            label = { Text("Telefono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button (
            onClick = onGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (enModoEdicion) "Actualizar Usuario" else "Guardar Usuario")
        }
    }
}

@Composable
fun PantallaListaUsuarios(
    usuarios: List<Usuario>,
    onActualizar: (Usuario) -> Unit,
    onEliminar: (Usuario) -> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column (
                    modifier = Modifier.weight(2f)
                ) {
                    Text(text = "ID: ${usuario.id}")
                    Text(text = "Nombre: ${usuario.nombre}")
                    Text(text = "Correo: ${usuario.correo}")
                    Text(text = "Edad: ${usuario.edad}")
                    Text(text = "Teléfono: ${usuario.telefono}")
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { onActualizar(usuario) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Editar")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onEliminar(usuario) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Borrar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    AppBaseDeDatosTheme {
        MiApp(ayudanteBaseDatos)
    }
}