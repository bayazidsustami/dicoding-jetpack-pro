package com.dicoding.submission.jetpack.data.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource.TvShowDataSourceImpl
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy
import com.dicoding.submission.jetpack.utils.EspressoIdlingResource
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TvShowRepository(
    private val remoteDataSource: TvShowDataSourceImpl,
    private val coroutineScope: CoroutineScope
) {
    fun getDiscoverTv(): LiveData<Result<List<TvShowsEntity>>>{
        val tvResult = MutableLiveData<Result<List<TvShowsEntity>>>()
        //EspressoIdlingResource.increment() //TODO don't forget to comment before build
        coroutineScope.launch {
            remoteDataSource.getDiscoverTv().onStart {
                tvResult.postValue(Result.Loading(data = null))
            }.catch {
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                tvResult.postValue(Result.Error(data = null, message = it.message))
            }.collect {
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                val dataToMap = it.results
                val afterMap = dataToMap?.mapNotNull { result ->
                    TvShowsEntity(
                        result?.id.toString(),
                        "${DataDummy.BASE_POSTER_PATH}${result?.posterPath}",
                        result?.originalName!!
                    )
                }
                tvResult.postValue(Result.Success(data = afterMap as List<TvShowsEntity>))
            }
        }
        return tvResult
    }

    fun getDetailTv(idTv: String): LiveData<Result<DetailTvShowsEntity>>{
        val detailResult = MutableLiveData<Result<DetailTvShowsEntity>>()
        //EspressoIdlingResource.increment() //TODO don't forget to comment before build
        coroutineScope.launch {
            remoteDataSource.getDetailTv(idTv).onStart {
                detailResult.postValue(Result.Loading(data = null))
            }.catch {
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                detailResult.postValue(Result.Error(data = null, message = it.message))
            }.collect { results ->
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                val dataMap = DetailTvShowsEntity(
                    results.id.toString(),
                    "${DataDummy.BASE_POSTER_PATH}${results.posterPath}",
                    results.homepage!!,
                    results.originalName!!,
                    results.overview!!,
                    results.status!!,
                    results.numberOfEpisodes?.toString()!!
                )
                detailResult.postValue(Result.Success(dataMap))
            }
        }
        return detailResult
    }
}