package com.dicoding.submission.jetpack.data.dataSource

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.local.LocalDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.ApiResult
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy.BASE_POSTER_PATH
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource.TvShowDataSource,
    private val localDataSource: LocalDataSource.TvShowDataSource,
    private val coroutineScope: CoroutineScope
) {
    fun getDiscoverTv(): LiveData<Result<PagedList<TvShowsEntity>>>{
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, BaseListResponse<ResultsItemTv>>(coroutineScope){
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(PAGE_SIZE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getListTv(false), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): LiveData<ApiResult<BaseListResponse<ResultsItemTv>>> {
                return remoteDataSource.getDiscoverTv()
            }

            override suspend fun saveCallResult(data: BaseListResponse<ResultsItemTv>) {
                val mapData = data.results?.mapNotNull {
                    TvShowsEntity(
                        id = it?.id?.toString()!!,
                        posterPath = "${BASE_POSTER_PATH}${it.posterPath}",
                        title = it.originalName!!,
                        isFavorite = false
                    )
                }

                if (mapData != null){
                    localDataSource.insertTvShow(mapData)
                }
            }
        }.asLiveData()
    }

    fun getDetailTv(idTv: String): LiveData<Result<DetailTvShowsEntity>>{
        return object : NetworkBoundResource<DetailTvShowsEntity, DetailTvShowResponse>(coroutineScope){
            override fun loadFromDB(): LiveData<DetailTvShowsEntity> {
                return localDataSource.getDetailTv(idTv)
            }

            override fun shouldFetch(data: DetailTvShowsEntity?): Boolean {
                return data == null
            }

            override suspend fun createCall(): LiveData<ApiResult<DetailTvShowResponse>> {
                return remoteDataSource.getDetailTv(idTv)
            }

            override suspend fun saveCallResult(data: DetailTvShowResponse) {
                val mapData = DetailTvShowsEntity(
                    id = data.id.toString(),
                    posterPath = "${BASE_POSTER_PATH}${data.posterPath}",
                    homepage = data.homepage!!,
                    title = data.originalName!!,
                    overview = data.overview!!,
                    status = data.status!!,
                    episodeCount = data.numberOfEpisodes.toString()
                )

                localDataSource.insertDetailTvShow(mapData)
            }
        }.asLiveData()
    }

    fun updateTvShow(tvShow: TvShowsEntity, isFavorite: Boolean){
        coroutineScope.launch(Dispatchers.IO){
            tvShow.isFavorite = isFavorite
            localDataSource.updateTvShow(tvShow)
        }
    }

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowsEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.getListTv(true), config).build()
    }

    companion object{
        private const val PAGE_SIZE = 5
    }
}