package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherapp.ui.theme.Typography

@Composable
fun CustomTextTitle(
    titleRes: Int,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = stringResource(titleRes),
        style = Typography.headlineMedium,
        textAlign = textAlign,
        modifier = Modifier.fillMaxWidth()
    )
}