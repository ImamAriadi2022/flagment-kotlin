package com.kimora.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kimora.app.screens.AboutScreen
import com.kimora.app.screens.AddScreen
import com.kimora.app.screens.EditScreen
import com.kimora.app.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("add") {
            AddScreen(navController)
        }
        composable("about") {
            AboutScreen(navController)
        }
        composable(
            route = "edit_screen/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            EditScreen(navController = navController, noteId = noteId)
        }
    }
}
