package com.example.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.theme.BackgroundProgressBar
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Preview(showBackground = true)
@Composable
private fun PreviewCustomProgressFullScreen() {
    WeatherAppTheme { CustomProgressFullScreen() }
}

@Composable
fun CustomProgressFullScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundProgressBar)
            .clickable(interactionSource = null, indication = null) {},
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}