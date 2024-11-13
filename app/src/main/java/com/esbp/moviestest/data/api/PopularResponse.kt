package com.esbp.moviestest.data.api

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("results") val popularResults: List<MovieDTO>? = null,
    @SerializedName("total_results") val totalResults: Int? = null
)