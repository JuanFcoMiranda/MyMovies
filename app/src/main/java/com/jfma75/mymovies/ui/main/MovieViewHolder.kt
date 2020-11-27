package com.jfma75.mymovies.ui.main

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.R
import com.jfma75.mymovies.databinding.ViewMovieBinding
import com.jfma75.mymovies.extensions.load

class MovieViewHolder(private val itemBinding: ViewMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(movie: Movie) {
        itemBinding.movieTitle.text = movie.title
        if (movie.posterPath.isNotBlank()) {
            val imageLoader = ImageLoader(itemView.context) {
                componentRegistry {
                    if (SDK_INT >= P) {
                        add(ImageDecoderDecoder())
                    } else {
                        add(GifDecoder())
                    }
                }
            }
            itemBinding.movieCover.load(movie.posterPath, imageLoader) {
                crossfade(true)
                placeholder(R.mipmap.loading)
            }
        }
    }
}