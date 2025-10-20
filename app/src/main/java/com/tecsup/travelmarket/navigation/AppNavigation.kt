package com.tecsup.travelmarket.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tecsup.travelmarket.ui.theme.components.BottomNavigationBar
import com.tecsup.travelmarket.ui.theme.screens.*
import com.tecsup.travelmarket.viewmodel.LugarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val lugarViewModel: LugarViewModel = viewModel()

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("home", "search", "profile")) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen(navController = navController) }
            composable("register") { RegisterScreen(navController = navController) }
            composable("home") { HomeScreen(navController = navController) }
            composable("search") { SearchScreen(navController = navController) }
            composable("profile") {
                ProfileScreen(navController = navController, lugarViewModel = lugarViewModel)
            }
            composable(
                route = "detail/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.StringType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")
                if (itemId != null) {
                    DetailScreen(
                        navController = navController,
                        itemId = itemId,
                        lugarViewModel = lugarViewModel
                    )
                }
            }
        }
    }
}