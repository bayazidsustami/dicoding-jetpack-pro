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
        var id: String,

        @ColumnInfo(name = "posterPath")
        var posterPath: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "releaseDate")
        var releaseDate: String,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
): Parcelable