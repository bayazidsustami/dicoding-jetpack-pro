package com.dicoding.submission.jetpack.data.tvShows

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowsEntity(
        val id: String,
        val posterPath: String,
        val title: String
): Parcelable
