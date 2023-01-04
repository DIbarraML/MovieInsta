package com.example.presentation.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.Movie
import com.example.presentation.R
import com.example.presentation.commons.loadImageOrFallback
import com.example.presentation.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetFragment(private val movie: Movie) : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI()
        println("MOVIE BOTTOM SHEET-> ${movie.id}")
    }

    private fun updateUI() {
        binding.posterImage.loadImageOrFallback(movie.posterPath, R.drawable.ic_baseline_movie_24)
        binding.titleText.text = movie.title
        binding.yearText.text = movie.releaseDate
        binding.runtimeText.visibility = View.GONE
        binding.overviewText.text = movie.overview
    }

    companion object {
        const val TAG: String = "MODAL_BOTTOM"
        fun newInstance(movie: Movie) = ModalBottomSheetFragment(movie)
    }
}