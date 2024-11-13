package com.esbp.moviestest.di

import com.esbp.moviestest.data.datasources.RemoteDataSource
import com.esbp.moviestest.data.datasources.RemoteDataSourceImpl
import com.esbp.moviestest.data.repository.MovieRepositoryImpl
import com.esbp.moviestest.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesMovieRepository(
        moviesRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun providesRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataSource
}