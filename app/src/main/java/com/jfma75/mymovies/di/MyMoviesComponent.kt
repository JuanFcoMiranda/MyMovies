package com.jfma75.mymovies.di

import android.app.Application
import com.jfma75.mymovies.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyMoviesComponent {
    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule) : DetailActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyMoviesComponent
    }
}