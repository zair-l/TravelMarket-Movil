package com.tecsup.travelmarket.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.model.Evento
import com.tecsup.travelmarket.data.model.Lugar
import com.tecsup.travelmarket.data.model.Restaurante
import com.tecsup.travelmarket.data.model.Transporte
import com.tecsup.travelmarket.data.repository.LocalRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    itemId: String
    ) {

    val repositorio = LocalRepository()

    val item: Any? = when {
        itemId.startsWith("e") -> repositorio.getEventoById(itemId)
        itemId.startsWith("r") -> repositorio.getRestauranteById(itemId)
        itemId.startsWith("t") -> repositorio.getTransporteById(itemId)
        else -> repositorio.getLugarById(itemId)
    }

    if (item == null) {
        Scaffold(
            topBar = {  }
        ) { paddingValues ->
            Text("Error: Item no encontrado", Modifier.padding(paddingValues).padding(16.dp))
        }
        return
    }

    when (item) {
        is Lugar -> LugarDetailContent(navController, item)
        is Evento -> EventoDetailContent(navController, item)
        is Restaurante -> RestauranteDetailContent(navController, item)
        is Transporte -> TransporteDetailContent(navController, item)
        else -> {
            Text("Error, categoría no reconocida", Modifier.padding(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDetailContent(
    navController: NavController,
    nombre: String,
    descripcion: String,
    imagenId: Int,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(nombre) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Image(
                    painter = painterResource(id = imagenId),
                    contentDescription = nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        nombre,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        descripcion,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    content()

                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {  }, // Redirigir al usuario
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Enlace")
                    }
                }
            }
        }
    }
}

@Composable
fun LugarDetailContent(navController: NavController, lugar: Lugar) {
    BaseDetailContent(
        navController = navController,
        nombre = lugar.nombre,
        descripcion = lugar.descripcion,
        imagenId = lugar.imagenId
    ) {
        Text("Dirección: ${lugar.direccion}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Horario: ${lugar.horario}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun EventoDetailContent(navController: NavController, evento: Evento) {
    BaseDetailContent(
        navController = navController,
        nombre = evento.nombre,
        descripcion = evento.descripcion,
        imagenId = evento.imagenId
    ) {
        // Contenido ESPECÍFICO de Evento
        Text("Fecha: ${evento.fecha}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Hora: ${evento.hora}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Ubicación: ${evento.ubicacion}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun RestauranteDetailContent(navController: NavController, restaurante: Restaurante) {
    BaseDetailContent(
        navController = navController,
        nombre = restaurante.nombre,
        descripcion = restaurante.descripcion,
        imagenId = restaurante.imagenId
    ) {
        Text("Dirección: ${restaurante.direccion}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Tipo de Cocina: ${restaurante.tipoCocina}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Rango de Precio: ${restaurante.rangoPrecio}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun TransporteDetailContent(navController: NavController, transporte: Transporte) {
    BaseDetailContent(
        navController = navController,
        nombre = transporte.nombre,
        descripcion = transporte.descripcion,
        imagenId = transporte.imagenId
    ) {
        Text("Tipo: ${transporte.tipo}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Cobertura: ${transporte.cobertura}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    DetailScreen(navController = NavController(LocalContext.current), "e1")
}