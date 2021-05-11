package com.dicoding.submission.jetpack.data.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource.FilmDataSourceImpl
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FilmRepository constructor(
    val remoteDataSource: FilmDataSourceImpl,
    val coroutineScope: CoroutineScope
) {
    fun getDiscoverMovie(): LiveData<Result<List<MoviesEntity>>> {
        val movieListResult = MutableLiveData<Result<List<MoviesEntity>>>()
        coroutineScope.launch {
            remoteDataSource.getDiscoverFilm().onStart {
                movieListResult.postValue(Result.Loading<List<MoviesEntity>>(data = null))
            }.catch {
                movieListResult.postValue(Result.Error<List<MoviesEntity>>(data = null, message = it.message))
            }.collect {
                val dataToMap = it.results
                val afterMap = dataToMap?.mapNotNull { result ->
                    MoviesEntity(
                        result?.id.toString(),
                        result?.posterPath!!,
                        result.title!!,
                        result.releaseDate!!
                    )
                }
                movieListResult.postValue(Result.Success(data = afterMap as List<MoviesEntity>))
            }
        }
        return movieListResult
    }

    fun getDetailMovie(idMovie: String): LiveData<Result<DetailMovieEntity>>{
        val detailResult = MutableLiveData<Result<DetailMovieEntity>>()
        coroutineScope.launch {
            remoteDataSource.getDetailFilm(idMovie).onStart {
                detailResult.postValue(Result.Loading<DetailMovieEntity>(data = null))
            }.catch {
                detailResult.postValue(Result.Error<DetailMovieEntity>(data = null, message = it.message))
            }.collect { results ->
                val dataMap = DetailMovieEntity(
                    results.id.toString(),
                    results.homepage!!,
                    results.title!!,
                    results.overview!!,
                    results.status!!
                )
                detailResult.postValue(Result.Success(dataMap))
            }
        }
        return detailResult
    }
}