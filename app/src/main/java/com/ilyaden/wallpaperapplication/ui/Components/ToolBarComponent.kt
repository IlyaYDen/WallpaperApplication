package com.ilyaden.wallpaperapplication.ui.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ilyaden.wallpaperapplication.R
import com.ilyaden.wallpaperapplication.ui.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarComponent(navController: NavHostController) {
    Column(

        modifier = Modifier.height(65.dp),
    ) {

        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        Modifier.clickable {
                            navController.popBackStack()

                        },
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    //val item = mainNavigationPathItems.find { it.path.equals(navController2.currentDestination) }
                    Text(
                        text =  stringResource(id = R.string.wallpaperApplication),
                        color = Color.White
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            Modifier.clickable {
                                navController.navigate(Screen.SettingsScreen.route)
                            },
                            tint = Color.White
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            },

            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Blue
            )
        )
    }
}