package com.ilyaden.wallpaperapplication.ui.ImagesScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
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
        vm.clear()

    }
    val images = remember {vm.images}

    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(gridState) {
        vm.clear()

        snapshotFlow { gridState.firstVisibleItemIndex }.collect { index ->
            println("test5 " + index)
            if (index >= images.size-9 || images.size == 0) {
                vm.getImagesEvent(string)
            }
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        state = gridState
    ) {

        items(images.size) { group ->

            CardScreen(images[group].raw, Screen.SelectScreen.withArgs(group.toString())
                ,navController)
        }
    }

}