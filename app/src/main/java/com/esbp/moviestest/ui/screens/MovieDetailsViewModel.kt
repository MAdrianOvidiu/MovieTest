package com.esbp.moviestest.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.esbp.moviestest.data.repository.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    private val _movieDetails = MutableStateFlow<MovieEntity?>(
        Json.decodeFromString(checkNotNull(savedStateHandle.get<String>("serializedMovie"))))
    val movieDetails = _movieDetails.asStateFlow()
}