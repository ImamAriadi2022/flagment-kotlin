package com.kimora.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kimora.app.screens.about.AboutScreen
import com.kimora.app.screens.add.AddScreen
import com.kimora.app.screens.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("add") {
            AddScreen(navController = navController)
        }
        composable("about") {
            AboutScreen(navController = navController)
        }
    }
}