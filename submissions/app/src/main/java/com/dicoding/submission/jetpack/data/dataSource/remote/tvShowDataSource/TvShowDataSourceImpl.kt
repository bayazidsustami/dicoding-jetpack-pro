package com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.ApiResult
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv
import com.dicoding.submission.jetpack.network.ApiService
import com.dicoding.submission.jetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TvShowDataSourceImpl constructor(
    private val apiService: ApiService
): RemoteDataSource.TvShowDataSource {
    override suspend fun getDiscoverTv(): LiveData<ApiResult<BaseListResponse<ResultsItemTv>>> {
        EspressoIdlingResource.increment()
        val results = MutableLiveData<ApiResult<BaseListResponse<ResultsItemTv>>>()
        flow {
            val request = apiService.getDiscoverTv()
            emit(request)
        }.flowOn(Dispatchers.IO).catch {
            results.postValue(ApiResult.Error(data = null, message = it.message))
        }.collect {
            results.postValue(ApiResult.Success(data = it))
        }
        EspressoIdlingResource.decrement()
        return results
    }

    override suspend fun getDetailTv(idTv: String): LiveData<ApiResult<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
       val results = MutableLiveData<ApiResult<DetailTvShowResponse>>()
        flow {
            val request = apiService.getDetailTv(idTv)
            emit(request)
        }.flowOn(Dispatchers.IO).catch {
            results.postValue(ApiResult.Error(data = null, message = it.message))
        }.collect {
            results.postValue(ApiResult.Success(data = it))
        }
        EspressoIdlingResource.decrement()
        return results
    }
}