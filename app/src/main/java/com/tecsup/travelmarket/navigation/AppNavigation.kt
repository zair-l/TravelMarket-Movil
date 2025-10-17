package com.tecsup.travelmarket.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.travelmarket.ui.theme.screens.DetailsScreen
import com.tecsup.travelmarket.ui.theme.screens.EventsScreen
import com.tecsup.travelmarket.ui.theme.screens.LoginScreen
import com.tecsup.travelmarket.ui.theme.screens.MainScreen
import com.tecsup.travelmarket.ui.theme.screens.PlacesScreen
import com.tecsup.travelmarket.ui.theme.screens.RegisterScreen
import com.tecsup.travelmarket.ui.theme.screens.RestaurantsScreen
import com.tecsup.travelmarket.ui.theme.screens.TransportScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(onMenuClick: (String) -> Unit) {
    TopAppBar(
        title = { Text("TravelMarket") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBarScreen(onMenuClick = { route ->
                navController.navigate(route)
            })
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "login") {
                LoginScreen(navController = navController)
            }
            composable(route = "register") {
                RegisterScreen(navController = navController)
            }
            composable(route = "main") {
                MainScreen(navController = navController)
            }
            composable(route = "details") {
                DetailsScreen(navController = navController)
            }
            composable(route = "events") {
                EventsScreen(navController = navController)
            }
            composable(route = "places") {
                PlacesScreen(navController = navController)
            }
            composable(route = "restaurants") {
                RestaurantsScreen(navController = navController)
            }
            composable(route = "transport") {
                TransportScreen(navController = navController)
            }
        }
    }
    
}