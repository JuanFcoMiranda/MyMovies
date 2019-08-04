package com.jfma75.mymovies.server

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {
    @GET("?type=movie")
    fun listMoviesAsync(@Query("s") searchTerm: String, @Query("apikey") apiKey: String): Deferred<MoviesDbResult>

    @GET("?type=movie")
    fun getMovieAsync(@Query("i") imdbId: String, @Query("apikey") apiKey: String): Deferred<Movie>
}