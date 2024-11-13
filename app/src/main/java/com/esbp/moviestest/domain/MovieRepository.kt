package com.esbp.moviestest.domain

import com.esbp.moviestest.data.repository.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(page: Int): Flow<Result<List<MovieEntity>>>
}