package com.esbp.moviestest.ui.screens

import com.esbp.moviestest.data.repository.MovieEntity
import com.esbp.moviestest.domain.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    private lateinit var repository: MovieRepository
    private lateinit var viewModel: MovieListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repository = mockk()
        viewModel = MovieListViewModel(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getMovies should update movies state with successful result`() = runTest {
        // Given
        val mockMovies = listOf(
            MovieEntity(id = 1, title = "Movie 1", overview = "", posterPath = "", releaseDate = "2024-01-01", voteAverage = 7.5f, voteCount = 100, popularity = 99.9f, adult = false, originalLanguage = "en", originalTitle = "Movie 1", backdropPath = "", video = false, genreIds = listOf(12))
        )
        val flow = flowOf(Result.success(mockMovies))
        coEvery { repository.getMovies(1) } returns flow

        // When
        viewModel.getMovies()
        advanceUntilIdle()

        // Then
        val finalMovies = viewModel.movies.first()
        assertEquals(mockMovies, finalMovies)
        coVerify { repository.getMovies(1) }
    }

    @Test
    fun `getMovies should not update movies state on failure`() = runTest {
        // Given
        val exception = Exception("Network error")
        val flow = flowOf(Result.failure<List<MovieEntity>>(exception))
        coEvery { repository.getMovies(1) } returns flow

        // When
        viewModel.getMovies()
        advanceUntilIdle()

        // Then
        val finalMovies = viewModel.movies.first()
        assertEquals(emptyList<MovieEntity>(), finalMovies)
        coVerify { repository.getMovies(1) }
    }
}
