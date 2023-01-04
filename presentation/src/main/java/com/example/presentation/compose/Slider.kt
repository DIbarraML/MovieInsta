package com.example.presentation.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.Genre

@Composable
private fun PaintPoint(modifier: Modifier) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = Color.White,
            radius = 10f
        )
    }
}

@Composable
fun LabelsSlider(labels: List<Genre>, modifier: Modifier) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        labels.forEachIndexed { index, genres ->
            Text(text = genres.name, style = MaterialTheme.typography.h5)
            if (index != labels.size - 1) {
                PaintPoint(
                    Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(5.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}