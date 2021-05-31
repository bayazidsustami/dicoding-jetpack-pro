package com.dicoding.submission.jetpack.data.dataSource

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.local.LocalDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.ApiResult
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.DataDummy.BASE_POSTER_PATH
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmRepository constructor(
    private val remoteDataSource: RemoteDataSource.FilmDataSource,
    private val localDataSource: LocalDataSource.FilmDataSource,
    private val coroutineScope: CoroutineScope
) {
    fun getDiscoverMovie(): LiveData<Result<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, BaseListResponse<ResultsItemMovie>>(coroutineScope){
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(PAGE_SIZE)
                    .setPageSize(PAGE_SIZE)
                    .build()

                return LivePagedListBuilder(localDataSource.getListFilm(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): LiveData<ApiResult<BaseListResponse<ResultsItemMovie>>> {
                return remoteDataSource.getDiscoverFilm()
            }

            override suspend fun saveCallResult(data: BaseListResponse<ResultsItemMovie>) {
                val mapData = data.results?.mapNotNull {
                    MoviesEntity(
                        id = it?.id?.toString()!!,
                        posterPath = "$BASE_POSTER_PATH${it.posterPath}",
                        title = it.title!!,
                        releaseDate = it.releaseDate!!
                    )
                }
                if (mapData != null){
                    localDataSource.insertListFilm(mapData)
                }
            }
        }.asLiveData()
    }

    fun getDetailMovie(idMovie: String): LiveData<Result<DetailMovieEntity>>{
        return object : NetworkBoundResource<DetailMovieEntity, DetailMovieResponse>(coroutineScope){
            override fun loadFromDB(): LiveData<DetailMovieEntity> {
                return localDataSource.getDetailFilm(idMovie)
            }

            override fun shouldFetch(data: DetailMovieEntity?): Boolean {
                return data == null
            }

            override suspend fun createCall(): LiveData<ApiResult<DetailMovieResponse>> {
                return remoteDataSource.getDetailFilm(idMovie)
            }

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val mapData = DetailMovieEntity(
                    id = data.id.toString(),
                    homepage = data.homepage!!,
                    title = data.title!!,
                    overview = data.overview!!,
                    status = data.status!!
                )
                localDataSource.insertDetailFilm(mapData)
            }
        }.asLiveData()
    }

    fun updateMovie(movie: MoviesEntity, isFavorite: Boolean){
        coroutineScope.launch(Dispatchers.IO) {
            movie.isFavorite = isFavorite
            localDataSource.updateFilm(movie)
        }
    }

    fun getFavoriteMovie(): LiveData<PagedList<MoviesEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()

        return LivePagedListBuilder(localDataSource.getListFilmFavorite(), config).build()
    }

    companion object{
        private const val PAGE_SIZE = 5
    }
}