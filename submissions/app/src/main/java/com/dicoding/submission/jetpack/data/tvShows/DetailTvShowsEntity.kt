package com.dicoding.submission.jetpack.data.tvShows

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailTvShowsEntity(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "tvId")
        val id: String,

        @ColumnInfo(name = "posterPath")
        val posterPath: String,

        @ColumnInfo(name = "homepage")
        val homepage: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "overview")
        val overview: String,

        @ColumnInfo(name = "status")
        val status: String,

        @ColumnInfo(name = "episodeCount")
        val episodeCount: String
)
