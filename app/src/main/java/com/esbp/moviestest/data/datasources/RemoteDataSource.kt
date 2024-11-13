package com.esbp.moviestest.data.datasources

import com.esbp.moviestest.data.api.PopularResponse

interface RemoteDataSource {
    suspend fun fetchMovies(page: Int): PopularResponse
}