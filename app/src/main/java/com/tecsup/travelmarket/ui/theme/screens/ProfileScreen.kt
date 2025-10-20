package com.tecsup.travelmarket.ui.theme.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.data.model.Evento
import com.tecsup.travelmarket.data.model.Lugar
import com.tecsup.travelmarket.ui.theme.BluePrimary
import com.tecsup.travelmarket.ui.theme.RedAction
import com.tecsup.travelmarket.viewmodel.LugarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    lugarViewModel: LugarViewModel
) {
    val favoritos = lugarViewModel.favoritos.value

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BluePrimary)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text("Mi Perfil", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("Información y favoritos", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = "User Icon", modifier = Modifier.size(40.dp), tint = BluePrimary)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Juan Pérez", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text("juan.perez@email.com", color = Color.Gray)
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración", tint = Color.Gray)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Configuración", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("login") {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                    ) {
                        Icon(Icons.Default.Logout, contentDescription = "Cerrar Sesión", tint = RedAction)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Cerrar Sesión", fontSize = 16.sp, color = RedAction)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favoritos", tint = RedAction)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mis Favoritos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    if (favoritos.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.FavoriteBorder, contentDescription = "No hay favoritos", tint = Color.Gray, modifier = Modifier.size(60.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No tienes favoritos", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Gray)
                            Text(
                                text = "Explora lugares y eventos para agregarlos a tus favoritos",
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 24.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(favoritos) { item ->
                                FavoriteItemCard(item = item) { itemId ->
                                    navController.navigate("detail/$itemId")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteItemCard(item: Any, onClick: (String) -> Unit) {
    val (id, nombre, descripcion) = when (item) {
        is Lugar -> Triple(item.id, item.nombre, item.categoria)
        is Evento -> Triple(item.id, item.nombre, item.categoria)
        else -> Triple("", "Desconocido", "")
    }

    if (id.isBlank()) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(id) },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorito", tint = RedAction)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(nombre, fontWeight = FontWeight.Bold)
                Text(descripcion, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}