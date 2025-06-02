package com.example.proyectodirectorio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectodirectorio.viewModels.ContactoViewModel
import com.example.proyectodirectorio.views.*


@Composable
fun NavManager(contactoVM: ContactoViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeView(navController, contactoVM)
        }

        composable("add") {
            AddView(navController, contactoVM)
        }

        composable(
            "edit/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val idContacto = backStackEntry.arguments?.getLong("id") ?: 0L

            EditView(
                navController = navController,
                viewModel = contactoVM,
                id = idContacto
            )
        }
    }
}