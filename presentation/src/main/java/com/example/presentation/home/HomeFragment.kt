package com.example.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.repository.MediaRepositoryImpl
import com.example.domain.Movie
import com.example.presentation.R
import com.example.presentation.bottomsheet.ModalBottomSheetFragment
import com.example.presentation.commons.loadImageOrFallback
import com.example.presentation.compose.LabelsSlider
import com.example.presentation.compose.ListMovieCard
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.theme.MovieInstaTheme
import com.example.usecases.GetGenresMedia
import com.example.usecases.GetPopularMedia
import com.example.usecases.GetSimilarMedia
import com.example.usecases.GetTrendingMedia

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initServices()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movieSlider.observe(viewLifecycleOwner) { movie ->
            binding.apply {
                containerSlider.isVisible = true
                layoutSlider.imageSlider.loadImageOrFallback(
                    movie.posterPath,
                    R.drawable.ic_baseline_movie_24
                )
                layoutSlider.title.text = movie.title
                layoutSlider.imageSlider.setOnClickListener {
                    launchBottomSheet(movie)
                }

                layoutSlider.labels.setContent {
                    MovieInstaTheme() {
                        LabelsSlider(
                            labels = viewModel.findGenresMoviesById(movie.genreIds),
                            modifier = Modifier
                        )
                    }
                }
            }
        }

        binding.layoutSlider.myList?.setOnClickListener {
            Toast.makeText(context, R.string.add_my_list, Toast.LENGTH_SHORT).show()
        }

        binding.composeView.setContent {
            MovieInstaTheme {
                Column() {
                    ListMovieCard(title = R.string.movies_popular_title, list = viewModel.movies)
                    { movie -> launchBottomSheet(movie) }
                    ListMovieCard(title = R.string.series_popular_title, list = viewModel.seriesTv)
                    { movie -> launchBottomSheet(movie) }
                    ListMovieCard(
                        title = R.string.movies_trending_title,
                        list = viewModel.moviesTrending
                    ) { movie -> launchBottomSheet(movie) }
                    ListMovieCard(
                        title = R.string.series_trending_title,
                        list = viewModel.seriesTrending
                    ) { movie -> launchBottomSheet(movie) }
                }

            }
        }
    }

    private fun launchBottomSheet(movie: Movie) =
        ModalBottomSheetFragment(movie)
            .show(parentFragmentManager, ModalBottomSheetFragment.TAG)


    private fun initServices() {
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
    }


}

class HomeViewModelFactory(private val context: Context) :
    AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val repository = MediaRepositoryImpl(
            MovieRemoteDataSource(context = context)
        )
        return HomeViewModel(
            GetPopularMedia(repository = repository),
            GetTrendingMedia(repository = repository),
            GetSimilarMedia(repository = repository),
            GetGenresMedia(repository = repository)
        ) as T
    }
}