package com.dicoding.submission.jetpack.data.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="detailmoviesentity")
data class DetailMovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        val id: String,

        @ColumnInfo(name = "homePage")
        val homepage: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "overview")
        val overview: String,

        @ColumnInfo(name = "status")
        val status: String
)