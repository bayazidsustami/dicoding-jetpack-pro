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
        var id: String,

        @ColumnInfo(name = "posterPath")
        var posterPath: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean
): Parcelable
