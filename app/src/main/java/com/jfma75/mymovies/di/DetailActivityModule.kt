package com.jfma75.mymovies.di

import com.jfma75.mymovies.data.repositories.MoviesRepository
import com.jfma75.mymovies.ui.detail.DetailViewModel
import com.jfma75.mymovies.usecases.GetMovieById
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule (private val movieId: String) {
    @Provides
    fun detailViewModelProvider(findMovieById: GetMovieById): DetailViewModel { return DetailViewModel(movieId, findMovieById) }

    @Provides
    fun findMovieByIdProvider(moviesRepository: MoviesRepository) = GetMovieById(moviesRepository)
}

@Subcomponent(modules = [(DetailActivityModule::class)])
interface DetailActivityComponent {
    val detaiViewModel: DetailViewModel
}