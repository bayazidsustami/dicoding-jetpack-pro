package com.dicoding.submission.jetpack.data.dataSource.local

import androidx.paging.DataSource
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    interface FilmDataSource{
        fun getListFilm(isFavorite: Boolean): DataSource.Factory<Int, MoviesEntity>
        fun getDetailFilm(idFilm: String): Flow<DetailMovieEntity>
    }

    interface TvShowDataSource{
        suspend fun getListTv(isFavorite: Boolean): Flow<List<TvShowsEntity>>
        suspend fun getDetailTv(idTv: String): Flow<DetailTvShowsEntity>
    }
}