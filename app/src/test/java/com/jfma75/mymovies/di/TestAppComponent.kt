package com.jfma75.mymovies.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, DataModule::class])
interface TestAppComponent