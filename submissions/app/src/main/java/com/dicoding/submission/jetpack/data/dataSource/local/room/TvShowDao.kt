package com.dicoding.submission.jetpack.data.dataSource.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshowentity WHERE isFavorite = :isFavorite")
    fun getListTv(isFavorite: Boolean): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM detailtvshowentity WHERE tvId = :tvId")
    fun getDetailTv(tvId: String): LiveData<DetailTvShowsEntity>

    @Update
    suspend fun updateTv(tvShow: TvShowsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tvShows: List<TvShowsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailTv(tvShow: DetailTvShowsEntity)
}