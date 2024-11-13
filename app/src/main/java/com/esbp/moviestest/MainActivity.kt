package com.esbp.moviestest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.esbp.moviestest.ui.screens.MovieDetailsRoute
import com.esbp.moviestest.ui.screens.MovieDetailsScreen
import com.esbp.moviestest.ui.screens.MovieListRoute
import com.esbp.moviestest.ui.screens.MovieListScreen
import com.esbp.moviestest.ui.screens.navigateToMovieDetailsScreen
import com.esbp.moviestest.ui.theme.MoviesTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTestTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MovieListRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<MovieListRoute> {
                            MovieListScreen(onMovieClick = { movie ->
                                navController.navigateToMovieDetailsScreen(movie)
                            })
                        }
                        composable<MovieDetailsRoute> { backstackEntry ->
                            val movie = backstackEntry.toRoute<MovieDetailsRoute>()
                            MovieDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}