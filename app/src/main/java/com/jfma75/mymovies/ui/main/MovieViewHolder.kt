package com.jfma75.mymovies.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.extensions.loadUrl
import kotlinx.android.synthetic.main.view_movie.view.*

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: Movie) {
        itemView.movieTitle.text = movie.title
        if (movie.posterPath.isNotBlank())
            itemView.movieCover.loadUrl(movie.posterPath)
    }
}