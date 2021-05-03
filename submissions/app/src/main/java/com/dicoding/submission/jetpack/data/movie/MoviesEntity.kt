package com.dicoding.submission.jetpack.data.movie

data class MoviesEntity(
        val id: String,
        val posterPath: String,
        val overview: String,
        val title: String,
        val releaseDate: String
)