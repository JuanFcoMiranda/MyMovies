package com.jfma75.mymovies.di

import android.app.Application
import com.jfma75.mymovies.R
import com.jfma75.mymovies.data.datasource.LocalDataSource
import com.jfma75.mymovies.data.datasource.ObjectBoxDataSource
import com.jfma75.mymovies.data.datasource.RemoteDataSource
import com.jfma75.mymovies.data.entities.MyObjectBox
import com.jfma75.mymovies.server.TheMovieDbDataSource
import com.jfma75.mymovies.ui.common.ConnectionLiveData
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideBoxStore(app: Application): BoxStore = MyObjectBox.builder().androidContext(app.applicationContext).build()

    @Provides
    fun localDataSourceProvider(db: BoxStore): LocalDataSource = ObjectBoxDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = TheMovieDbDataSource()

    @Provides
    @Singleton
    fun getConnectionData(app: Application): ConnectionLiveData = ConnectionLiveData(app)
}