package com.dicoding.submission.jetpack.data.dataSource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

interface LocalDataSource {
    interface FilmDataSource{
        fun getListFilm(): DataSource.Factory<Int, MoviesEntity>
        fun getListFilmFavorite(): DataSource.Factory<Int, MoviesEntity>
        fun getDetailFilm(idFilm: String): LiveData<DetailMovieEntity>

        suspend fun updateFilm(film: MoviesEntity)
        suspend fun insertListFilm(films: List<MoviesEntity>)
        suspend fun insertDetailFilm(film: DetailMovieEntity)
    }

    interface TvShowDataSource{
        fun getListTv(): DataSource.Factory<Int, TvShowsEntity>
        fun getListFavoriteTv(): DataSource.Factory<Int, TvShowsEntity>
        fun getDetailTv(idTv: String): LiveData<DetailTvShowsEntity>

        suspend fun updateTvShow(tvShow: TvShowsEntity)
        suspend fun insertTvShow(tvShows: List<TvShowsEntity>)
        suspend fun insertDetailTvShow(tvShow: DetailTvShowsEntity)
    }
}