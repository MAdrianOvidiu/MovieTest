package com.esbp.moviestest.data.repository

import com.esbp.moviestest.data.api.MovieDTO
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieEntityKtTest{

    @Test
    fun `toMovieEntity should map MovieDTO properties to MovieEntity correctly`() {
        // Given
        val movieDTO = MovieDTO(
            id = 123,
            title = "Sample Movie",
            overview = "A sample movie overview.",
            posterPath = "/samplepath.jpg",
            releaseDate = "2024-01-01",
            voteAverage = 8.5f,
            voteCount = 100,
            popularity = 500.0f,
            adult = false,
            originalLanguage = "en",
            originalTitle = "Sample Original Title",
            backdropPath = "/samplebackdrop.jpg",
            video = false,
            genreIds = listOf(12, 18, 28),
            page = 1
        )

        // When
        val movieEntity = movieDTO.toMovieEntity()

        // Then
        assertEquals(movieDTO.id, movieEntity.id)
        assertEquals(movieDTO.title, movieEntity.title)
        assertEquals(movieDTO.overview, movieEntity.overview)
        assertEquals(movieDTO.posterPath, movieEntity.posterPath)
        assertEquals(movieDTO.releaseDate, movieEntity.releaseDate)
        assertEquals(movieDTO.voteAverage, movieEntity.voteAverage)
        assertEquals(movieDTO.voteCount, movieEntity.voteCount)
        assertEquals(movieDTO.popularity, movieEntity.popularity)
        assertEquals(movieDTO.adult, movieEntity.adult)
        assertEquals(movieDTO.originalLanguage, movieEntity.originalLanguage)
        assertEquals(movieDTO.originalTitle, movieEntity.originalTitle)
        assertEquals(movieDTO.backdropPath, movieEntity.backdropPath)
        assertEquals(movieDTO.video, movieEntity.video)
        assertEquals(movieDTO.genreIds, movieEntity.genreIds)
    }
}