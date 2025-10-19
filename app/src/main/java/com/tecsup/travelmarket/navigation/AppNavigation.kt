package com.tecsup.travelmarket.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// Ojo con la ruta de tus imports, puede que necesites ajustarla
import com.tecsup.travelmarket.ui.theme.components.BottomNavigationBar
import com.tecsup.travelmarket.ui.theme.screens.DetailScreen
import com.tecsup.travelmarket.ui.theme.screens.HomeScreen
import com.tecsup.travelmarket.ui.theme.screens.LoginScreen
import com.tecsup.travelmarket.ui.theme.screens.ProfileScreen
import com.tecsup.travelmarket.ui.theme.screens.RegisterScreen
import com.tecsup.travelmarket.ui.theme.screens.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("home", "search", "profile")) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login", // La app ahora empieza en el Login
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("register") {
                RegisterScreen(navController = navController)
            }

            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("search") {
                SearchScreen(navController = navController)
            }
            composable("profile") {
                ProfileScreen()
            }
            composable(
                route = "detail/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.StringType })
            ) {
                val itemId = navBackStackEntry?.arguments?.getString("itemId")

                if (itemId != null) {
                    DetailScreen(
                        navController = navController,
                        itemId = itemId
                    )
                } else {
                    Text("Error: ID del item no encontrado")
                }
            }
        }
    }
}