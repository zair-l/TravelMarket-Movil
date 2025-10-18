package com.tecsup.travelmarket.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.travelmarket.ui.theme.components.BottomNavigationBar
import com.tecsup.travelmarket.ui.theme.screens.DetailsScreen
import com.tecsup.travelmarket.ui.theme.screens.LoginScreen
import com.tecsup.travelmarket.ui.theme.screens.MainScreen
import com.tecsup.travelmarket.ui.theme.screens.ProfileScreen
import com.tecsup.travelmarket.ui.theme.screens.RegisterScreen
import com.tecsup.travelmarket.ui.theme.screens.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "busqueda",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "busqueda") {
                SearchScreen(navController = navController)
            }
            composable(route = "home") {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Pantalla Home")
                }
            }
            composable(route = "perfil") {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Pantalla Perfil")
                }
            }
            composable(route = "details") {
                DetailsScreen(navController = navController)
            }
        }
    }

}