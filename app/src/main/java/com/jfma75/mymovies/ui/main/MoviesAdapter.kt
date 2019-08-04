package com.jfma75.mymovies.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.R
import com.jfma75.mymovies.extensions.inflate

class MoviesAdapter (private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {
    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.inflate(R.layout.view_movie, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }
}