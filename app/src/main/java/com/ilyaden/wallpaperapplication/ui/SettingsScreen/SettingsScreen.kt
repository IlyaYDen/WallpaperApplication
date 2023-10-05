package com.ilyaden.wallpaperapplication.ui.SettingsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ilyaden.wallpaperapplication.R


@Composable
fun SettingsScreen(theme: MutableState<Boolean>) {
    Column() {
        SettingsElement(stringResource(id = R.string.DarkTheme), theme) {
            theme.value = !theme.value
        }
    }
}

@Composable
fun SettingsElement(stringResource: String, active :MutableState<Boolean>, function: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource,
            style = LocalTextStyle.current.copy(
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontSize = 20.0.sp
            ),
            modifier = Modifier.weight(3f))
        Switch(checked = active.value, onCheckedChange = function)

    }
}
