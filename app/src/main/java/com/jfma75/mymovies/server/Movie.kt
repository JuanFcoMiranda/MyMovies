package com.jfma75.mymovies.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Plot")
    val plot: String?,
    @SerializedName("Poster")
    val posterPath: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Runtime")
    val duration: String,
    @SerializedName("Ratings")
    val ratings: List<Rating>?
) : Parcelable