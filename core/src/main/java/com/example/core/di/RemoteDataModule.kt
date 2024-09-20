package com.example.core.di

import com.example.core.data.remote.network.ApiConfig
import com.example.core.data.remote.network.ApiServiceMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideMovieApiService () : ApiServiceMovie = ApiConfig.getPopulerApiService()
}