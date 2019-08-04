package com.jfma75.mymovies.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jfma75.mymovie.domain.Rating
import kotlinx.android.synthetic.main.rating_layout.view.*

class RatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(rating: Rating) {
        itemView.source.text = rating.source
        itemView.value.text = rating.value
    }
}
