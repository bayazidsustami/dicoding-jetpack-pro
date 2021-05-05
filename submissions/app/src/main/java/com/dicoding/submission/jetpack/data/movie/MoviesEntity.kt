package com.dicoding.submission.jetpack.data.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesEntity(
        val id: String,
        val posterPath: String,
        val title: String,
        val releaseDate: String
): Parcelable