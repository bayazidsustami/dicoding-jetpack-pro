package com.dicoding.submission.jetpack.data.movie

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "moviesentity")
@Parcelize
data class MoviesEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        val id: String,

        @ColumnInfo(name = "posterPath")
        val posterPath: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "releaseDate")
        val releaseDate: String,

        @ColumnInfo(name = "isFavorite")
        val isFavorite: Boolean = false
): Parcelable