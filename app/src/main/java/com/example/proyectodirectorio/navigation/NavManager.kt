package com.example.proyectodirectorio.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectodirectorio.viewModels.ContactoViewModel
import com.example.proyectodirectorio.views.*
import androidx.compose.ui.platform.LocalContext
import com.example.proyectodirectorio.dataStore.StoreBoarding
import com.example.proyectodirectorio.onBoardViews.MainOnBoarding

@Composable
fun NavManager(contactoVM: ContactoViewModel) {
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getStoreBoarding.collectAsState(initial = true)

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (store.value==true) "home" else "splash"
    ) {
        composable("home") {
            HomeView(navController, contactoVM)
        }

        composable("splash") {
            SplashScreen(navController = navController, store.value)
        }

        composable("onBoarding") {
            MainOnBoarding(navController, dataStore)
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