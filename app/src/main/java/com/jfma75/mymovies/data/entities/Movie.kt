package com.jfma75.mymovies.data.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Movie(
    @Id
    var id: Long = 0,
    var title: String,
    val year: Int,
    val imdbID: String,
    val plot: String?,
    var posterPath: String,
    var director: String,
    var duration: String,
    var ratings: List<Rating>?
)