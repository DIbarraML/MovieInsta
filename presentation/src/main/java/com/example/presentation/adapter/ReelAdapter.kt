package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Media
import com.example.presentation.R
import com.example.presentation.commons.loadImageOrFallback
import com.example.presentation.databinding.ItemMovieCardBinding

class ReelAdapter(
    private val list: List<Media>,
    private val onClick: (Media) -> Unit
) : RecyclerView.Adapter<ReelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(
        private val binding: ItemMovieCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(media: Media, onClick: (Media) -> Unit) {
            binding.image.loadImageOrFallback(media.posterPath, R.drawable.ic_baseline_movie_24)
            binding.image.setOnClickListener {
                onClick.invoke(media)
            }
        }
    }
}