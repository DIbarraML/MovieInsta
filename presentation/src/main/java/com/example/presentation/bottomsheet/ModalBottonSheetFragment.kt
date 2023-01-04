package com.example.presentation.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.domain.Media
import com.example.presentation.R
import com.example.presentation.commons.loadImageOrFallback
import com.example.presentation.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetFragment() : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val media: Media = arguments?.getSerializable("media") as Media
        println("onViewCreated  -> $media")
        updateUI(media)
    }

    private fun updateUI(media: Media) {
        binding.apply {
            posterImage.loadImageOrFallback(media.posterPath, R.drawable.ic_baseline_movie_24)
            titleText.text = media.title
            yearText.text = media.releaseDate
            runtimeText.visibility = View.GONE
            overviewText.text = media.overview
            content.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("media", media)
                findNavController().navigate(
                    R.id.action_modalBottomSheetFragment_to_previewFragment,
                    bundle
                )
            }
        }
    }

    companion object {
        const val TAG: String = "MODAL_BOTTOM"
        fun newInstance() = ModalBottomSheetFragment()
    }
}