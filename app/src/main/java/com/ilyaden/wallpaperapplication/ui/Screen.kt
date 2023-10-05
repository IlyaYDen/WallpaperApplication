package com.ilyaden.wallpaperapplication.ui

sealed class Screen(val route: String) {
    object CategoriesScreen : Screen("categories_screen")
    object ImagesScreen : Screen("images_screen")
    object SelectScreen : Screen("select_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}