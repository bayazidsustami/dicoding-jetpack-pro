package com.dicoding.submission.jetpack.data.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource.FilmDataSourceImpl
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.DataDummy.BASE_POSTER_PATH
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FilmRepository constructor(
    private val remoteDataSource: FilmDataSourceImpl,
    private val coroutineScope: CoroutineScope
) {
    fun getDiscoverMovie(): LiveData<Result<List<MoviesEntity>>> {
        //EspressoIdlingResource.increment() //TODO don't forget to comment before build
        val movieListResult = MutableLiveData<Result<List<MoviesEntity>>>()
        coroutineScope.launch {
            remoteDataSource.getDiscoverFilm().onStart {
                movieListResult.postValue(Result.Loading<List<MoviesEntity>>(data = null))
            }.catch {
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                movieListResult.postValue(Result.Error<List<MoviesEntity>>(data = null, message = it.message))
            }.collect {
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                val dataToMap = it.results
                val afterMap = dataToMap?.mapNotNull { result ->
                    MoviesEntity(
                        result?.id.toString(),
                        "$BASE_POSTER_PATH${result?.posterPath}",
                        result?.title!!,
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
        //EspressoIdlingResource.increment() //TODO don't forget to comment before build
        coroutineScope.launch {
            remoteDataSource.getDetailFilm(idMovie).onStart {
                detailResult.postValue(Result.Loading<DetailMovieEntity>(data = null))
            }.catch {
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
                detailResult.postValue(Result.Error<DetailMovieEntity>(data = null, message = it.message))
            }.collect { results ->
                //EspressoIdlingResource.decrement() //TODO don't forget to comment before build
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