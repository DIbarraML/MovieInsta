package com.example.movieinsta.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieinsta.R

@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(id = R.color.black_gradient))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.align(CenterVertically)
        )

        toolbarMainItem().forEach {
            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(CenterVertically)
            )
        }
    }
}

private fun toolbarMainItem() = listOf("Series", "Movies")
