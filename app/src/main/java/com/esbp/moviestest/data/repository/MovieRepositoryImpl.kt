package com.esbp.moviestest.data.repository

import com.esbp.moviestest.data.datasources.RemoteDataSource
import com.esbp.moviestest.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    //private val localDataSource: LocalDataSource,
) : MovieRepository {

    override suspend fun getMovies(page: Int): Flow<Result<List<MovieEntity>>> = flow {
        val response = remoteDataSource.fetchMovies(page)
        response.popularResults
            ?.map { it.toMovieEntity() }
            ?.let { emit(Result.success(it)) }
            ?: emit(Result.failure(Exception("No popular results found")))
    }
}