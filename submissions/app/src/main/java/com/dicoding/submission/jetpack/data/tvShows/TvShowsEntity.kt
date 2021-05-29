package com.dicoding.submission.jetpack.data.tvShows

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvshowentity")
@Parcelize
data class TvShowsEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "tvId")
        val id: String,

        @ColumnInfo(name = "posterPath")
        val posterPath: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "isFavorite")
        val isFavorite: String
): Parcelable
