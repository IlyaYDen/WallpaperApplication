package com.ilyaden.wallpaperapplication.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilyaden.wallpaperapplication.ui.CategoriesScreen.CategoriesScreen
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesScreen
import com.ilyaden.wallpaperapplication.ui.SelectScreen.SelectScreen


@Composable
fun Navigation(mainActivity: MainActivity) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CategoriesScreen.route) {
        composable(route = Screen.CategoriesScreen.route) {
            CategoriesScreen(navController)
        }
        composable(
            route = Screen.ImagesScreen.route + "/{category}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ){ entry ->
            ImagesScreen(navController,entry.arguments?.getString("category")?: "nothing")
        }
        composable(
            route = Screen.SelectScreen.route + "/{url}",
            arguments = listOf(
                navArgument("url") { type = NavType.IntType }
            )
        ) { entry ->
            var t = entry.arguments?.getInt("url")
            if(t != null)
                SelectScreen(t,mainActivity)
        }
    }
}