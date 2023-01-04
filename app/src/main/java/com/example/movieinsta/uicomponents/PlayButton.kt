package com.example.movieinsta.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieinsta.R
import com.example.presentation.theme.MovieInstaTheme

@Composable
fun PlayButton(modifier: Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White)
            .width(104.dp)
            .height(40.dp)
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = R.drawable.arrow_play), contentDescription = "")
        Text(text = "Play", style = MaterialTheme.typography.h4, color = Color.Black)
    }
}

@Composable
fun FindDownloadButton() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White)
            .height(40.dp)
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Find Something to Download",
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
    }
}

@Composable
fun MyListButton(
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = false, onClick = {})
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            tint = Color.White,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "My List",
            fontSize = 10.sp,
            style = MaterialTheme.typography.button,
            color = Color.White,
            maxLines = 1
        )
    }
}

@Composable
fun InfoButton(
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = false, onClick = {})
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "info",
            fontSize = 10.sp,
            style = MaterialTheme.typography.button,
            maxLines = 1,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewButton() {
    MovieInstaTheme {
        PlayButton(Modifier)
        FindDownloadButton()
    }
}