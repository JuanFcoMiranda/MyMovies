package com.jfma75.mymovies.data.datasource

import com.jfma75.mymovie.domain.Movie

interface RemoteDataSource {
    suspend fun getMovies(searchTerm: String, apiKey: String): List<Movie>?
    suspend fun getMovie(imdbId: String, apiKey: String): Movie
}