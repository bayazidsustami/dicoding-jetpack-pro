package com.dicoding.submission.jetpack.data.dataSource.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM moviesentity WHERE isFavorite = :isFavorite")
    fun getListFilm(isFavorite: Boolean): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM detailmoviesentity WHERE movieId = :movieId")
    fun getDetailFilm(movieId: String): Flow<DetailMovieEntity>

}