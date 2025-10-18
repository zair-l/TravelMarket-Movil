package com.tecsup.travelmarket.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.travelmarket.ui.theme.components.BottomNavigationBar
import com.tecsup.travelmarket.ui.theme.screens.DetailScreen
import com.tecsup.travelmarket.ui.theme.screens.HomeScreen
import com.tecsup.travelmarket.ui.theme.screens.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("search") {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Pantalla de Búsqueda")
                }
            }
            composable("profile") {
                ProfileScreen()
            }
            composable("detail") {
                DetailScreen(navController = navController)
            }
        }
    }
}