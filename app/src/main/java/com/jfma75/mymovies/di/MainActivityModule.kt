package com.jfma75.mymovies.di

import com.jfma75.mymovies.data.repositories.MoviesRepository
import com.jfma75.mymovies.ui.main.MainViewModel
import com.jfma75.mymovies.usecases.GetMovies
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule {
    @Provides
    fun mainViewModelProvider(getMovies: GetMovies) = MainViewModel(getMovies)

    @Provides
    fun getMoviesProvider(moviesRepository: MoviesRepository) = GetMovies(moviesRepository)
}

@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}