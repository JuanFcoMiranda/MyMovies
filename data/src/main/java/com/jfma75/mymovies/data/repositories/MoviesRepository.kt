package com.jfma75.mymovies.data.repositories

import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.data.datasource.LocalDataSource
import com.jfma75.mymovies.data.datasource.RemoteDataSource

class MoviesRepository(private val localDataSource: LocalDataSource,
                       private val remoteDataSource: RemoteDataSource,
                       private val apiKey: String) {
    suspend fun findById(id: Long) = localDataSource.findById(id)

    suspend fun findByImdbId(id: String) = remoteDataSource.getMovie(id, apiKey)

    suspend fun save(movie: Movie) = localDataSource.save(movie)

    suspend fun isEmpty() = localDataSource.isEmpty()

    suspend fun findAll(searchTerm: String): List<Movie> {
        /*if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.getMovies(apiKey)
            localDataSource.saveAll(movies)
        }

        return localDataSource.findAll()*/

        return remoteDataSource.getMovies(searchTerm, apiKey) ?: emptyList()
    }
}