package com.jfma75.mymovies.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jfma75.mymovie.domain.Rating
import com.jfma75.mymovies.R
import com.jfma75.mymovies.extensions.inflate

class RatingsAdapter : RecyclerView.Adapter<RatingViewHolder>() {
    var ratings: List<Rating> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val view = parent.inflate(R.layout.rating_layout, false)
        return RatingViewHolder(view)
    }

    override fun getItemCount(): Int = ratings.size

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val rating = ratings[position]
        holder.bind(rating)
    }
}