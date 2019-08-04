package com.jfma75.mymovies.data.datasource

import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.data.mappers.toDomain
import com.jfma75.mymovies.data.mappers.toObjectBox
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.jfma75.mymovies.data.entities.Movie as DomainMovie

class ObjectBoxDataSource(db: BoxStore) : LocalDataSource {
    private val movieDao = db.boxFor<DomainMovie>()

    override suspend fun findById(id: Long): Movie = withContext(Dispatchers.IO) {
        movieDao.get(id).toDomain()
    }

    override suspend fun findAll(): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.all.map {movie -> movie.toDomain() }
    }

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        movieDao.isEmpty
    }

    override suspend fun save(item: Movie): Long = withContext(Dispatchers.IO) {
        movieDao.put(item.toObjectBox())
    }

    override suspend fun saveAll(items: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.put(items.map { movie -> movie.toObjectBox() })
    }

    override suspend fun update(item: Movie): Long {
        return withContext(Dispatchers.IO) { movieDao.put(item.toObjectBox()) }
    }
}