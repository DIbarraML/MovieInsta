package com.example.movieinsta

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
                            finish()
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieInstaTheme {
    }
}