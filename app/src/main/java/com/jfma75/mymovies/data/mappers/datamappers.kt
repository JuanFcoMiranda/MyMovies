package com.jfma75.mymovies.data.mappers

import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovie.domain.Rating
import com.jfma75.mymovies.data.entities.Movie as DomainMovie
import com.jfma75.mymovies.data.entities.Rating as DomainRating
import com.jfma75.mymovies.server.Movie as ServerMovie
import com.jfma75.mymovies.server.Rating as ServerRating

fun Movie.toObjectBox(): DomainMovie = DomainMovie(id, title, year, imdbID, plot, posterPath, director ?: "", duration ?: "", ratings?.map { it.toObjectBox() })

fun DomainMovie.toDomain(): Movie = Movie(id, title, year, imdbID, plot, posterPath, director, duration, ratings?.map { it.toDomain() })

fun Rating.toObjectBox(): DomainRating = DomainRating(id, source, value)

fun DomainRating.toDomain(): Rating = Rating(id, source, value)

fun ServerMovie.toDomain(): Movie = Movie(0, title, year, imdbID, plot, posterPath, director, duration, ratings?.map { it.toDomain() })

fun ServerRating.toDomain(): Rating = Rating(0, source, value)