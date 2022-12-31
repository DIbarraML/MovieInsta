package com.example.movieinsta

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.commons.Output
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.repository.MovieRepositoryImpl
import com.example.movieinsta.ui.theme.MovieInstaTheme
import com.example.movieinsta.uicomponents.BottomNavigationBar
import com.example.movieinsta.uicomponents.MovieCard
import com.example.movieinsta.uicomponents.NavigationItemModel
import com.example.movieinsta.uicomponents.Slider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieInstaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())

                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String) {
    Column {
        val url = "https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg"
        val urlPreview =
            "https://ca-times.brightspotcdn.com/dims4/default/182be96/2147483647/strip/true/crop/7040x3520+0+0/resize/1486x743!/quality/80/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2Fdf%2Fda%2Fcdd4db0e4e95af32f06d38554bfe%2Fst3-production-still-8.jpg"
        /*PreviewItem(urlPreview)
        WatchingItem(url)
        PlayButton(Modifier)
        FindDownloadButton()*/
        //NavigationItem(id = R.drawable.icon_home, title = "Home")
        val list = listOf<NavigationItemModel>(
            NavigationItemModel(icon = R.drawable.icon_home, title = "Home"),
            NavigationItemModel(icon = R.drawable.icon_search, title = "Search"),
            NavigationItemModel(icon = R.drawable.icon_download, title = "Download")

        )
        GlobalScope.launch {
            val movieRepository = MovieRepositoryImpl(
                MovieRemoteDataSource()
            )
            val result = movieRepository.getMovies("es", 1)
            when (result) {
                is Output.Success -> {
                    println("LISTAS -> ${result.value}")
                }
                is Output.Failure -> {
                }
            }
            println("INFO -> ${result.toString()}")
        }


        BottomNavigationBar(list)
        MovieCard(url)
        Slider("https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieInstaTheme {
        Greeting("Android")
    }
}