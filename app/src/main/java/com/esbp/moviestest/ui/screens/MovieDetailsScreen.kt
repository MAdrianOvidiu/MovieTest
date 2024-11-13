package com.esbp.moviestest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.esbp.moviestest.data.repository.MovieEntity
import com.esbp.moviestest.ui.theme.Purple40
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class MovieDetailsRoute(val serializedMovie: String)

fun NavController.navigateToMovieDetailsScreen(movie: MovieEntity) {
    val serializedMovie = Json.encodeToString(movie)
    this.navigate(MovieDetailsRoute(serializedMovie))
}

@Composable
fun MovieDetailsScreen(vm: MovieDetailsViewModel = hiltViewModel()) {
    val movieDetails by vm.movieDetails.collectAsStateWithLifecycle()

    MovieDetailsContent(movieDetails)
}

@Composable
fun MovieDetailsContent(movieDetails: MovieEntity?) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (movieDetails != null) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors().copy(containerColor = Purple40),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = movieDetails.title,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Release date - ${movieDetails.releaseDate}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Original language - ${movieDetails.originalLanguage}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "Popularity rating - ${movieDetails.popularity}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun MovieDetailsPreview() {
    MovieDetailsContent(null)
}