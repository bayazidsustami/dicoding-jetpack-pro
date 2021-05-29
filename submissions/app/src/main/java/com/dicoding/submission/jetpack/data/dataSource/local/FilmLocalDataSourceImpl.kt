package com.dicoding.submission.jetpack.data.dataSource.local

import androidx.paging.DataSource
import com.dicoding.submission.jetpack.data.dataSource.local.room.FilmDao
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import kotlinx.coroutines.flow.Flow

class FilmLocalDataSourceImpl(
    private val filmDao: FilmDao
): LocalDataSource.FilmDataSource {
    override fun getListFilm(isFavorite: Boolean): DataSource.Factory<Int, MoviesEntity> {
        return filmDao.getListFilm(isFavorite)
    }

    override fun getDetailFilm(idFilm: String): Flow<DetailMovieEntity> {
        return filmDao.getDetailFilm(idFilm)
    }
}