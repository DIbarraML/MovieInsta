package com.example.presentation.preview

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.commons.Output
import com.example.domain.Media
import com.example.usecases.GetSimilarMedia
import kotlinx.coroutines.launch

class PreviewViewModel(
    private val getSimilarMedia: GetSimilarMedia
) : ViewModel() {

    val mediaSimilar = mutableStateListOf<Media>()

    fun getSimilarMedia(mediaId: Int, mediaType: String) {
        viewModelScope.launch {
            when (val output = getSimilarMedia.invoke(
                language = LANG_ESP,
                mediaType = mediaType,
                mediaId = mediaId
            )) {
                is Output.Success -> {
                    mediaSimilar.addAll(output.value.media)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    companion object {
        private const val LANG_ESP = "es"
    }
}