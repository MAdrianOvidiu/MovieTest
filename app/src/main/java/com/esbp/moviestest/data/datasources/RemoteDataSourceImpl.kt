package com.esbp.moviestest.data.datasources

import com.esbp.moviestest.data.api.MovieDBService
import com.esbp.moviestest.data.api.PopularResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val movieDBService: MovieDBService) : RemoteDataSource {
    override suspend fun fetchMovies(page: Int): PopularResponse {
        return movieDBService.getMovieList(page)
    }
}