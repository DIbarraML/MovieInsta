package com.example.movieinsta

import android.app.Application
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.repository.MediaRepositoryImpl
import com.example.usecases.GetGenresMedia
import com.example.usecases.GetPopularMedia
import com.example.usecases.GetSimilarMedia
import com.example.usecases.GetTrendingMedia

class HomeViewModelFactory(private val application: Application) :
    AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val repository = MediaRepositoryImpl(
            MovieRemoteDataSource(context = application.baseContext)
        )
        return HomeViewModel(
            GetPopularMedia(repository = repository),
            GetTrendingMedia(repository = repository),
            GetSimilarMedia(repository = repository),
            GetGenresMedia(repository = repository)
        ) as T
    }
}