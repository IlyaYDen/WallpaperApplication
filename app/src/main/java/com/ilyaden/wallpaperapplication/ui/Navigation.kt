package com.ilyaden.wallpaperapplication.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilyaden.wallpaperapplication.di.DIContainer
import com.ilyaden.wallpaperapplication.ui.CategoriesScreen.CategoriesScreen
import com.ilyaden.wallpaperapplication.ui.Components.ToolBarComponent
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesScreen
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesViewModel
import com.ilyaden.wallpaperapplication.ui.SelectScreen.SelectScreen
import com.ilyaden.wallpaperapplication.ui.SettingsScreen.SettingsScreen


@Composable
fun Navigation(mainActivity: MainActivity, theme: MutableState<Boolean>) {
    val navController = rememberNavController()

    Column() {


        ToolBarComponent(navController)

        NavHost(navController = navController, startDestination = Screen.CategoriesScreen.route) {
            composable(route = Screen.CategoriesScreen.route) {

                BackHandler(true) {
                    // do nothing
                }

                CategoriesScreen(navController)
            }
            composable(
                route = Screen.ImagesScreen.route + "/{category}",
                arguments = listOf(
                    navArgument("category") { type = NavType.StringType }
                )
            ) { entry ->

                ImagesScreen(navController, entry.arguments?.getString("category") ?: "nothing")
            }
            composable(route = Screen.SettingsScreen.route){
                SettingsScreen(theme)
            }
            composable(
                route = Screen.SelectScreen.route + "/{url}",
                arguments = listOf(
                    navArgument("url") { type = NavType.IntType }
                )
            ) { entry ->
                var t = entry.arguments?.getInt("url")
                if (t != null)
                    SelectScreen(t, mainActivity)
            }
        }
    }
}