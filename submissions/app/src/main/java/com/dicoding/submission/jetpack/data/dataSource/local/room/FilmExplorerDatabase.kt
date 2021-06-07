package com.dicoding.submission.jetpack.data.dataSource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

@Database(
    entities = [MoviesEntity::class, DetailMovieEntity::class, TvShowsEntity::class, DetailTvShowsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmExplorerDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun tvShowDao(): TvShowDao
}