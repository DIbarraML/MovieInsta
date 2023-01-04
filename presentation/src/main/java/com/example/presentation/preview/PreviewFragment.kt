package com.example.presentation.preview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.repository.MediaRepositoryImpl
import com.example.domain.Media
import com.example.presentation.R
import com.example.presentation.commons.loadImageOrFallback
import com.example.presentation.compose.GridMovieCard
import com.example.presentation.databinding.FragmentPreviewBinding
import com.example.usecases.GetSimilarMedia

class PreviewFragment() : Fragment() {

    lateinit var previewBinding: FragmentPreviewBinding
    private val viewModel: PreviewViewModel by viewModels {
        PreviewModelFactory(context = requireContext())
    }

    companion object {
        fun newInstance(media: Media) = PreviewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        previewBinding = FragmentPreviewBinding.inflate(inflater)
        return previewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val media: Media = arguments?.getSerializable("media") as Media

        viewModel.getSimilarMedia(mediaId = media.id, mediaType = media.mediaType)

        previewBinding.apply {
            posterImage.loadImageOrFallback(media.backdropPath, R.drawable.ic_baseline_movie_24)
            title.text = media.title
            date.text = media.releaseDate
            overviewText.text = media.overview
        }

        previewBinding.composeView.setContent {
            GridMovieCard(list = viewModel.mediaSimilar, onclick = {
                launchBottomSheet(it)
            })
        }
    }

    private fun launchBottomSheet(media: Media) {
        val bundle = Bundle()
        bundle.putSerializable("media", media)
        findNavController().navigate(
            R.id.action_previewFragment_to_modalBottomSheetFragment, bundle
        )
    }

}

class PreviewModelFactory(private val context: Context) :
    AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val repository = MediaRepositoryImpl(
            MovieRemoteDataSource(context = context)
        )
        return PreviewViewModel(
            GetSimilarMedia(repository = repository)
        ) as T
    }
}