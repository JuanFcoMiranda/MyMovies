package com.jfma75.mymovies.server

import com.google.gson.annotations.SerializedName

open class MoviesDbResult(
    @SerializedName("Search")
    val search: List<Movie>?,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("Response")
    val response: Boolean
)