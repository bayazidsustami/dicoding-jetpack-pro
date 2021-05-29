package com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource

import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FilmDataSourceImpl  constructor(
    private val apiService: ApiService
): RemoteDataSource.FilmDataSource {
    override suspend fun getDiscoverFilm(): Flow<BaseListResponse<ResultsItemMovie>> {
        return flow {
            val request = apiService.getDiscoverMovie()
            emit(request)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailFilm(idMovie: String): Flow<DetailMovieResponse> {
        return flow {
            val request = apiService.getDetailMovie(idMovie)
            emit(request)
        }.flowOn(Dispatchers.IO)
    }
}