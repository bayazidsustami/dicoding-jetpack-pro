package com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.ApiResult
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FilmDataSourceImpl  constructor(
    private val apiService: ApiService
): RemoteDataSource.FilmDataSource {
    override suspend fun getDiscoverFilm(): LiveData<ApiResult<BaseListResponse<ResultsItemMovie>>> {
        val results = MutableLiveData<ApiResult<BaseListResponse<ResultsItemMovie>>>()
        flow {
            val request = apiService.getDiscoverMovie()
            emit(request)
        }.flowOn(Dispatchers.IO).catch {
            results.postValue(ApiResult.Error(data = null, message = it.message))
        }.collect {
            results.postValue(ApiResult.Success(data = it))
        }
        return results
    }

    override suspend fun getDetailFilm(idMovie: String): LiveData<ApiResult<DetailMovieResponse>> {
        val results = MutableLiveData<ApiResult<DetailMovieResponse>>()
        flow {
            val request = apiService.getDetailMovie(idMovie)
            emit(request)
        }.flowOn(Dispatchers.IO).catch {
            results.postValue(ApiResult.Error(data = null, message = it.message))
        }.collect {
            results.postValue(ApiResult.Success(data = it))
        }
        return results
    }
}