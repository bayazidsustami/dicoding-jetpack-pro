package com.dicoding.submission.jetpack.data.dataSource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.submission.jetpack.data.dataSource.local.room.FilmDao
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity

class FilmLocalDataSourceImpl(
    private val filmDao: FilmDao
): LocalDataSource.FilmDataSource {
    override fun getListFilm(isFavorite: Boolean): DataSource.Factory<Int, MoviesEntity> {
        return filmDao.getListFilm(isFavorite)
    }

    override fun getDetailFilm(idFilm: String): LiveData<DetailMovieEntity> {
        return filmDao.getDetailFilm(idFilm)
    }

    override suspend fun insertListFilm(films: List<MoviesEntity>) {
        filmDao.insertListFilm(films)
    }

    override suspend fun insertDetailFilm(film: DetailMovieEntity) {
        filmDao.insertDetailFilm(film)
    }
}