package com.esbp.moviestest.data.repository

import com.esbp.moviestest.data.api.MovieDTO
import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val voteCount: Int,
    val popularity: Float,
    val adult: Boolean,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val video: Boolean,
    val genreIds: List<Int>?
)

fun MovieDTO.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        adult = adult,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        video = video,
        genreIds = genreIds
    )
}