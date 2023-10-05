package com.ilyaden.wallpaperapplication.ui.ImagesScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ilyaden.wallpaperapplication.di.DIContainer
import com.ilyaden.wallpaperapplication.ui.Components.CardScreen
import com.ilyaden.wallpaperapplication.ui.Screen

lateinit var vm : ImagesViewModel
@Composable
fun ImagesScreen(navController: NavHostController, string: String) {

    vm = DIContainer.resolveDependency(ImagesViewModel::class.java) //viewModel<ImagesViewModel>()

    LaunchedEffect(vm) {
        vm.getImagesEvent(string)
    }
    val images = remember {vm.images}


    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)) {

        items(images.size) { group ->

            CardScreen(images[group].raw, Screen.SelectScreen.withArgs(group.toString())
                ,navController)
        }
    }
}