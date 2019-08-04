package com.jfma75.mymovies.usecases

import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.data.repositories.MoviesRepository

class GetMovieById(val moviesRepository: MoviesRepository) {
    suspend fun invoke(id: String): Movie = moviesRepository.findByImdbId(id)
}
