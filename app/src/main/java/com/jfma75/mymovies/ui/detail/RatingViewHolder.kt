package com.jfma75.mymovies.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.jfma75.mymovie.domain.Rating
import com.jfma75.mymovies.databinding.RatingLayoutBinding

class RatingViewHolder(private val itemBinding: RatingLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(rating: Rating) {
        itemBinding.source.text = rating.source
        itemBinding.value.text = rating.value
    }
}