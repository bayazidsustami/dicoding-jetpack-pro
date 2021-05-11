package com.dicoding.submission.jetpack.data.dataSource

import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {
    interface FilmDataSource{
        suspend fun getDiscoverFilm(): Flow<BaseListResponse>
        suspend fun getDetailFilm(idMovie: String): Flow<DetailMovieResponse>
    }
    interface TvShowDataSource{
        suspend fun getDiscoverTv(): Flow<BaseListResponse>
        suspend fun getDetailTv(idTv: String): Flow<DetailTvShowResponse>
    }
}