package com.jfma75.mymovies.usecases

import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.data.repositories.MoviesRepository

class GetMovies (val moviesRepository: MoviesRepository) {
    suspend fun invoke(searchTearm: String): List<Movie> = moviesRepository.findAll(searchTearm)
}