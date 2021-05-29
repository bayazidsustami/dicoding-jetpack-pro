package com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource

import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv
import com.dicoding.submission.jetpack.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvShowDataSourceImpl constructor(
    private val apiService: ApiService
): RemoteDataSource.TvShowDataSource {
    override suspend fun getDiscoverTv(): Flow<BaseListResponse<ResultsItemTv>> {
        return flow {
            val request = apiService.getDiscoverTv()
            emit(request)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailTv(idTv: String): Flow<DetailTvShowResponse> {
        return flow {
            val request = apiService.getDetailTv(idTv)
            emit(request)
        }.flowOn(Dispatchers.IO)
    }
}