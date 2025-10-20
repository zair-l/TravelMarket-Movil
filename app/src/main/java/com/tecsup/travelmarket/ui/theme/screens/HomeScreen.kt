package com.tecsup.travelmarket.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.data.repository.LocalRepository
import com.tecsup.travelmarket.ui.theme.BluePrimary
import com.tecsup.travelmarket.ui.theme.BlueSecondary
import com.tecsup.travelmarket.ui.theme.GreenCategory
import com.tecsup.travelmarket.ui.theme.OrangeCategory
import com.tecsup.travelmarket.ui.theme.PurpleCategory
import com.tecsup.travelmarket.ui.theme.components.CategoryCard
import com.tecsup.travelmarket.ui.theme.components.sharedItemList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val repositorio = LocalRepository()
    val allItems = repositorio.getAllItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BluePrimary)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text("TravelMarket", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Descubre lo mejor de Lima", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
            }
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Categorías",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(240.dp),
                    userScrollEnabled = false
                ) {
                    item {
                        CategoryCard(title = "Lugares", icon = Icons.Default.LocationOn, color = BlueSecondary) {
                            navController.navigate("search?categoryId=Lugares")
                        }
                    }
                    item {
                        CategoryCard(title = "Eventos", icon = Icons.Default.Event, color = PurpleCategory) {
                            navController.navigate("search?categoryId=Eventos")
                        }
                    }
                    item {
                        CategoryCard(title = "Gastronomía", icon = Icons.Default.Restaurant, color = OrangeCategory) {
                            navController.navigate("search?categoryId=Gastronomía")
                        }
                    }
                    item {
                        CategoryCard(title = "Transporte", icon = Icons.Default.DirectionsBus, color = GreenCategory) {
                            navController.navigate("search?categoryId=Transporte")
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Recomendados para ti",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),)
        }
        sharedItemList(
            items = allItems,
            onItemClick = { itemId ->
                navController.navigate("detail/$itemId")
            }
        )
    }

}