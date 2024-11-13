package com.esbp.moviestest.data.repository

import com.esbp.moviestest.data.api.MovieDTO
import com.esbp.moviestest.data.api.PopularResponse
import com.esbp.moviestest.data.datasources.RemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: MovieRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        remoteDataSource = mockk()
        repository = MovieRepositoryImpl(remoteDataSource)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getMovies should emit success result with list of MovieEntity on successful response`() = runTest {
        // Given
        val mockResponse = PopularResponse(popularResults = mockMovieList)
        coEvery { remoteDataSource.fetchMovies(1) } returns mockResponse

        // When
        val result = repository.getMovies(1).first()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMovieList.map { it.toMovieEntity() }, result.getOrNull())
    }

    @Test
    fun `getMovies should emit failure result when popularResults is null`() = runTest {
        // Given
        val mockResponse = PopularResponse(popularResults = null)
        coEvery { remoteDataSource.fetchMovies(1) } returns mockResponse

        // When
        val result = repository.getMovies(1).first()

        // Then
        assertTrue(result.isFailure)
        assertEquals("No popular results found", result.exceptionOrNull()?.message)
    }

    val mockMovieList = listOf(
        MovieDTO(
            adult = false,
            backdropPath = "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
            genreIds = listOf(878, 28, 12),
            id = 912649,
            originalLanguage = "en",
            originalTitle = "Venom: The Last Dance",
            overview = "Eddie and Venom are on the run...",
            popularity = 3930.216f,
            posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
            releaseDate = "2024-10-22",
            title = "Venom: The Last Dance",
            video = false,
            voteAverage = 6.387f,
            voteCount = 658,
            page = 1
        ),
        MovieDTO(
            adult = false,
            backdropPath = "/2fxnTXr8NwyTFkunkimJkGkhqfy.jpg",
            genreIds = listOf(18, 28, 27),
            id = 1118031,
            originalLanguage = "es",
            originalTitle = "Apocalipsis Z: el principio del fin",
            overview = "When a kind of rabies that transforms...",
            popularity = 2835.786f,
            posterPath = "/wIGJnIFQlESkC2rLpfA8EDHqk4g.jpg",
            releaseDate = "2024-10-04",
            title = "Apocalypse Z: The Beginning of the End",
            video = false,
            voteAverage = 6.8f,
            voteCount = 385,
            page = 1
        ),
        MovieDTO(
            adult = false,
            backdropPath = "/18TSJF1WLA4CkymvVUcKDBwUJ9F.jpg",
            genreIds = listOf(27, 53, 9648),
            id = 1034541,
            originalLanguage = "en",
            originalTitle = "Terrifier 3",
            overview = "Five years after surviving Art the Clown's Halloween massacre...",
            popularity = 3304.003f,
            posterPath = "/l1175hgL5DoXnqeZQCcU3eZIdhX.jpg",
            releaseDate = "2024-10-09",
            title = "Terrifier 3",
            video = false,
            voteAverage = 6.9f,
            voteCount = 885,
            page = 1
        ),)
}