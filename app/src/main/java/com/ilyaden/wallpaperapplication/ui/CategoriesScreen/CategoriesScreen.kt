package com.ilyaden.wallpaperapplication.ui.CategoriesScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ilyaden.wallpaperapplication.di.DIContainer
import com.ilyaden.wallpaperapplication.ui.Components.CardScreen
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesViewModel
import com.ilyaden.wallpaperapplication.ui.Screen


var groups = arrayOf(
    "zoo","home","sea","city","mountains"
)
lateinit var vm : CategoriesViewModel
@Composable
fun CategoriesScreen(navController: NavHostController) {

    vm = DIContainer.resolveDependency(CategoriesViewModel::class.java) //viewModel<ImagesViewModel>()

    LaunchedEffect(vm) {

        vm.getImageEvent(groups.toList())
    }
    val images = remember { vm.images }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)) {

        items(images.size) { group ->
            CardScreen(
                images[group].raw,
                Screen.ImagesScreen.withArgs(groups[group]),
                navController = navController
            )
        }
    }
}
