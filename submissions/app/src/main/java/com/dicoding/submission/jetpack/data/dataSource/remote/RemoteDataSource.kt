package com.dicoding.submission.jetpack.data.dataSource.remote

import androidx.lifecycle.LiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    interface FilmDataSource{
         suspend fun getDiscoverFilm(): LiveData<ApiResult<BaseListResponse<ResultsItemMovie>>>
         suspend fun getDetailFilm(idMovie: String): LiveData<ApiResult<DetailMovieResponse>>
    }
    interface TvShowDataSource{
        suspend fun getDiscoverTv(): LiveData<ApiResult<BaseListResponse<ResultsItemTv>>>
        suspend fun getDetailTv(idTv: String): LiveData<ApiResult<DetailTvShowResponse>>
    }
}