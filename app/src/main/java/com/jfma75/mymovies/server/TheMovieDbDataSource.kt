package com.jfma75.mymovies.server

import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.data.datasource.RemoteDataSource
import com.jfma75.mymovies.data.mappers.toDomain

class TheMovieDbDataSource : RemoteDataSource {
    override suspend fun getMovies(searchTerm: String, apiKey: String): List<Movie>? {
        try {
            val movies = TheMovieDb.service.listMoviesAsync(searchTerm, apiKey).await()
            return movies.search?.map { it.toDomain() }
        } catch (e: Exception){
            return null
        }
    }

    override suspend fun getMovie(imdbId: String, apiKey: String): Movie {
        val movie = TheMovieDb.service.getMovieAsync(imdbId, apiKey).await()
        return movie.toDomain()
    }
}