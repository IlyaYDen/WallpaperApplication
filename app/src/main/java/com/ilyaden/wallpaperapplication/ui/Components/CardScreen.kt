package com.ilyaden.wallpaperapplication.ui.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ilyaden.wallpaperapplication.R

@Preview
@Composable
fun CardScreenPreview() {
    Column {

        Box(
            modifier = Modifier
                .padding(4.dp).height(400.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.wp3),
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier,
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Composable
fun CardScreen(img: String, navRoute: String, navController: NavHostController) {
    Column {

        Box(
            modifier = Modifier
                .padding(4.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            AsyncImage(// it is too difficult to load image without libs
                model = img,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp)
                    .clickable {
                        navController.navigate(navRoute)
                    },
                contentScale = ContentScale.Crop
            )
        }

    }
}