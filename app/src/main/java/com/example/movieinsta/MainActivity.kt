package com.example.movieinsta

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieinsta.uicomponents.*
import com.example.presentation.MovieInstaActivity
import com.example.presentation.theme.MovieInstaTheme

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(application = application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.getPopularMovies()
            viewModel.getPopularMovies()
            viewModel.getPopularTv()
            viewModel.getPopularTv()
            viewModel.getTrendingMovies()
            viewModel.getTrendingMovies()
            viewModel.getTrendingTv()
            viewModel.getTrendingTv()
            viewModel.getGenresMovies()
            viewModel.getGenresTv()
            MovieInstaTheme {
                // A surface container using the 'background' color from the theme

                Scaffold(
                    topBar = { Toolbar() },
                    bottomBar = { BottomNavigationBar() }
                ) {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {

                        Column {
                            val intent =
                                Intent(LocalContext.current, MovieInstaActivity::class.java)
                            startActivity(intent)
                        }
                        Home()
                    }
                }
            }
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Home() {

        Column() {
            /*PreviewItem(urlPreview)
            WatchingItem(url)
            PlayButton(Modifier)
            FindDownloadButton()*/
            //NavigationItem(id = R.drawable.icon_home, title = "Home")

            if (viewModel.moviesTrending.isNotEmpty()) {
                val movie = viewModel.getMediaSlider()
                val genres = viewModel.findGenresMoviesById(movie.genreIds)
                Slider(movie, genres)
            }

            ListMovieCard(
                title = R.string.movies_popular_title,
                list = viewModel.media
            ) { movie ->  }
            ListMovieCard(title = R.string.series_popular_title, list = viewModel.seriesTv) {}
            ListMovieCard(
                title = R.string.movies_trending_title,
                list = viewModel.moviesTrending
            ) {}
            ListMovieCard(
                title = R.string.series_trending_title,
                list = viewModel.seriesTrending
            ) {}

            //ListMovieCard(list = viewModel.movies)
            //ListMovieCard(list = viewModel.movies)
            /*ListMovieCard(title = "Peliculas", list = viewModel.movies)
            ListMovieCard(title = "Peliculas", list = viewModel.movies)*/
            /*val list = listOf<NavigationItemModel>(
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
                println("INFO -> $result")
            }


            BottomNavigationBar(list)
            MovieCard(url)
            Slider("https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg")*/
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieInstaTheme {
        //Home()
    }
}