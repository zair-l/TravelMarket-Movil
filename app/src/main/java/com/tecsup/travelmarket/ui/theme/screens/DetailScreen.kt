package com.tecsup.travelmarket.ui.theme.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.tecsup.travelmarket.data.model.Evento
import com.tecsup.travelmarket.data.model.Lugar
import com.tecsup.travelmarket.data.model.Restaurante
import com.tecsup.travelmarket.data.model.Transporte
import com.tecsup.travelmarket.data.repository.LocalRepository
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Commute
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.ui.graphics.vector.ImageVector
import com.tecsup.travelmarket.ui.theme.OrangeButton


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
        navController.popBackStack()
        return
    }

    when (item) {
        is Lugar -> LugarDetailContent(navController, item)
        is Evento -> EventoDetailContent(navController, item)
        is Restaurante -> RestauranteDetailContent(navController, item)
        is Transporte -> TransporteDetailContent(navController, item)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDetailContent(
    navController: NavController,
    nombre: String,
    descripcion: String,
    imagenId: Int,
    urlEnlace: String,
    categoria: String,
    content: @Composable () -> Unit
) {

    val context = LocalContext.current

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Image(
                        painter = painterResource(id = imagenId),
                        contentDescription = nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(Color.Black.copy(alpha = 0.3f), shape = CircleShape)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }

                    IconButton(
                        onClick = {  }, // Lógica para agregar a favoritos
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(Color.Black.copy(alpha = 0.3f), shape = CircleShape)
                    ) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Favoritos", tint = Color.White)
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        nombre,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = categoria,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        descripcion,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    content()

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlEnlace))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangeButton)
                    ) {
                        Icon(Icons.Default.OpenInBrowser, contentDescription = "Visitar Sitio Web", modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Visitar Sitio Web")
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
        urlEnlace = lugar.urlEnlace,
        categoria = lugar.categoria,
        imagenId = lugar.imagenId
    ) {
        DetailRow(Icons.Default.LocationOn, "Dirección", lugar.direccion)
        DetailRow(Icons.Default.Schedule, "Horario", lugar.horario)
        DetailRow(Icons.Default.AttachMoney, "Precio estimado", "S/. 15")
    }
}

@Composable
fun EventoDetailContent(navController: NavController, evento: Evento) {
    BaseDetailContent(
        navController = navController,
        nombre = evento.nombre,
        descripcion = evento.descripcion,
        urlEnlace = evento.urlEnlace,
        categoria = evento.categoria,
        imagenId = evento.imagenId
    ) {
        DetailRow(Icons.Default.LocationOn, "Ubicación", evento.ubicacion)
        DetailRow(Icons.Default.CalendarToday, "Fecha", evento.fecha)
        DetailRow(Icons.Default.Schedule, "Hora", evento.hora)
    }
}

@Composable
fun RestauranteDetailContent(navController: NavController, restaurante: Restaurante) {
    BaseDetailContent(
        navController = navController,
        nombre = restaurante.nombre,
        descripcion = restaurante.descripcion,
        urlEnlace = restaurante.urlEnlace,
        categoria = restaurante.categoria,
        imagenId = restaurante.imagenId
    ) {
        DetailRow(Icons.Default.LocationOn, "Dirección", restaurante.direccion)
        DetailRow(Icons.Default.Restaurant, "Tipo", restaurante.tipoCocina)
        DetailRow(Icons.Default.AttachMoney, "Precio estimado", restaurante.rangoPrecio)
    }
}

@Composable
fun TransporteDetailContent(navController: NavController, transporte: Transporte) {
    BaseDetailContent(
        navController = navController,
        nombre = transporte.nombre,
        descripcion = transporte.descripcion,
        urlEnlace = transporte.urlEnlace,
        categoria = transporte.categoria,
        imagenId = transporte.imagenId
    ) {
        DetailRow(Icons.Default.DirectionsBus, "tipo", transporte.tipo)
        DetailRow(Icons.Default.Commute, "Cobertura", transporte.cobertura)
    }
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(icon, contentDescription = label, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall)
            Text(value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    DetailScreen(navController = NavController(LocalContext.current), "e1")
}