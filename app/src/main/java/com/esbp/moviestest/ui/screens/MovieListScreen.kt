package com.esbp.moviestest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.esbp.moviestest.data.repository.MovieEntity
import com.esbp.moviestest.ui.theme.MoviesTestTheme
import com.esbp.moviestest.ui.theme.Purple40
import kotlinx.serialization.Serializable

@Serializable
object MovieListRoute

@Composable
fun MovieListScreen(vm: MovieListViewModel = hiltViewModel(), onMovieClick: (MovieEntity) -> Unit) {
    val movies by vm.movies.collectAsStateWithLifecycle()

    MovieListContent(movies, onMovieClick)
}

@Composable
fun MovieListContent(movies: List<MovieEntity>, onMovieClick: (MovieEntity) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item { Text(text = "Popular movies", style = MaterialTheme.typography.titleMedium) }
        item { Spacer(Modifier.height(16.dp)) }
        items(movies) { movie ->
            Card(
                onClick = { onMovieClick(movie) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors().copy(containerColor = Purple40),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = movie.releaseDate,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreview() {
    MoviesTestTheme {
        MovieListContent(emptyList(), {})
    }
}