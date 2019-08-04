package com.jfma75.mymovie.domain

data class Movie(
    val id: Long,
    val title: String,
    val year: Int,
    val imdbID: String,
    val plot: String?,
    val posterPath: String,
    val director: String?,
    val duration: String?,
    val ratings: List<Rating>?
)