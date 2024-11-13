package com.esbp.moviestest.ui.screens

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esbp.moviestest.data.repository.MovieEntity
import com.esbp.moviestest.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies: StateFlow<List<MovieEntity>> = _movies.asStateFlow()

    @VisibleForTesting
    fun getMovies() {
        viewModelScope.launch {
            repository.getMovies(1).collect { result ->
                result.onSuccess { movies ->
                    _movies.value = movies
                }
                result.onFailure {
                    // TODO: Handle error
                }
            }
        }
    }

    init {
        getMovies()
    }
}