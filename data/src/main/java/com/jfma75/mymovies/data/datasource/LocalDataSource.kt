package com.jfma75.mymovies.data.datasource

import com.jfma75.mymovie.domain.Movie

interface LocalDataSource {
    suspend fun findById(id: Long): Movie
    suspend fun findAll(): List<Movie>
    suspend fun isEmpty(): Boolean
    suspend fun save(item: Movie): Long
    suspend fun saveAll(items: List<Movie>)
    suspend fun update(item: Movie) : Long
}