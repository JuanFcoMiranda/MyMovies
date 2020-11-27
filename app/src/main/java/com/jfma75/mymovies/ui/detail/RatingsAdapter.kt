package com.jfma75.mymovies.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jfma75.mymovie.domain.Rating
import com.jfma75.mymovies.R
import com.jfma75.mymovies.databinding.RatingLayoutBinding
import com.jfma75.mymovies.extensions.inflate
import com.jfma75.mymovies.ui.main.MovieViewHolder

class RatingsAdapter : RecyclerView.Adapter<RatingViewHolder>() {
    var ratings: List<Rating> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val itemBinding = RatingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatingViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = ratings.size

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val rating = ratings[position]
        holder.bind(rating)
    }
}