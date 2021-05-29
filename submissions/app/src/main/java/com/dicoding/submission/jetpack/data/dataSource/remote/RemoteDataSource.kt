package com.dicoding.submission.jetpack.data.dataSource.remote

import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    interface FilmDataSource{
        suspend fun getDiscoverFilm(): Flow<BaseListResponse<ResultsItemMovie>>
        suspend fun getDetailFilm(idMovie: String): Flow<DetailMovieResponse>
    }
    interface TvShowDataSource{
        suspend fun getDiscoverTv(): Flow<BaseListResponse<ResultsItemTv>>
        suspend fun getDetailTv(idTv: String): Flow<DetailTvShowResponse>
    }
}