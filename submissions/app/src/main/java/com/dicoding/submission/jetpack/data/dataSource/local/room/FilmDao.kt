package com.dicoding.submission.jetpack.data.dataSource.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM moviesentity WHERE isFavorite = :isFavorite")
    fun getListFilm(isFavorite: Boolean): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM detailmoviesentity WHERE movieId = :movieId")
    fun getDetailFilm(movieId: String): LiveData<DetailMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListFilm(films: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailFilm(film: DetailMovieEntity)

}