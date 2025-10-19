package com.tecsup.travelmarket.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.travelmarket.ui.theme.BluePrimary
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.model.Evento
import com.tecsup.travelmarket.data.model.Lugar
import com.tecsup.travelmarket.data.model.Restaurante
import com.tecsup.travelmarket.data.model.Transporte
import com.tecsup.travelmarket.data.repository.LocalRepository
import com.tecsup.travelmarket.ui.theme.components.EventoCard
import com.tecsup.travelmarket.ui.theme.components.RestauranteCard
import com.tecsup.travelmarket.ui.theme.components.TransporteCard


@Composable
fun SearchScreen(navController: NavController) {
    val respositorio = LocalRepository()
    val allItems = respositorio.getAllItems()

    Scaffold (
        topBar = { SearchTopBar() }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { SearchField() }
            item { Filter() }
            item { Text(text = "Resultados", style = MaterialTheme.typography.headlineMedium) }
            items(allItems) { item ->
                when (item) {
                    is Lugar -> {
                        LugarCard(
                            lugar = item,
                            onClick = {

                            }
                        )
                    }
                    is Evento -> {
                        EventoCard(
                            evento = item,
                            onClick = {

                            }
                        )
                    }
                    is Restaurante -> {
                        RestauranteCard(
                            restaurante = item,
                            onClick = {

                            }
                        )
                    }
                    is Transporte -> {
                        TransporteCard(
                            transporte = item,
                            onClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTopBar() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(BluePrimary)
            .padding(16.dp)
    ) {
        Text(
            text = "Buscar",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = "Encuentra tu próxima aventura",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField() {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        placeholder = { Text("Buscar lugares, eventos...") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Ícono de búsqueda")
        },
        shape = RoundedCornerShape(32.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter() {
    val categorias = listOf("Todos", "Lugares", "Eventos", "Gastronomía", "Transporte")

    Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Filtrar por categoría",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categorias) { categoria ->
                AssistChip(
                    onClick = {  },
                    label = { Text(categoria) },
                    colors = if (categoria == "Todos") {
                        AssistChipDefaults.assistChipColors(
                            containerColor = BluePrimary,
                            labelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        AssistChipDefaults.assistChipColors()
                    }
                )
            }
        }
    }
}


@Composable
fun LugarCard(lugar: Lugar, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = lugar.imagenId),
                contentDescription = lugar.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = lugar.nombre,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            text = lugar.categoria,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = lugar.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogingScreenPreview() {
    SearchScreen(navController = NavController(LocalContext.current))
}