package com.example.movieinsta.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieinsta.R
import com.example.movieinsta.ui.theme.MovieInstaTheme

@Composable
private fun NavigationItem(id: Int, contentDescription: String? = null, title: String) {
    Column(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.align(CenterHorizontally)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colorResource(id = R.color.background_navigation_bar)),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listItemNavigation().forEach {
            NavigationItem(id = it.icon, title = it.title)
        }
    }
}

data class NavigationItemModel(
    val icon: Int,
    val contentDescription: String? = null,
    val title: String
)

private fun listItemNavigation() = listOf(
    NavigationItemModel(icon = R.drawable.icon_home, title = "Home"),
    NavigationItemModel(icon = R.drawable.icon_search, title = "Search"),
    NavigationItemModel(icon = R.drawable.icon_download, title = "Download")
)

@Preview(showBackground = true)
@Composable
fun DefaultNavigationBar() {
    MovieInstaTheme {
        BottomNavigationBar()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultNavigationItem() {
    MovieInstaTheme {
        NavigationItem(id = R.drawable.icon_coming_soon, title = "Coming Soon")
    }
}
