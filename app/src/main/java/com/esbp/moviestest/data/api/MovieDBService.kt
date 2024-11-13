package com.esbp.moviestest.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {
    @GET("/3/movie/popular?language=en-US")
    suspend fun getMovieList(@Query("page") page: Int): PopularResponse
}