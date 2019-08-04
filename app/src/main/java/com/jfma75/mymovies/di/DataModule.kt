package com.jfma75.mymovies.di

import com.jfma75.mymovies.data.datasource.LocalDataSource
import com.jfma75.mymovies.data.datasource.RemoteDataSource
import com.jfma75.mymovies.data.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DataModule {
    @Provides
    fun moviesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        @Named("apiKey") apiKey: String
    ) = MoviesRepository(localDataSource, remoteDataSource, apiKey)
}