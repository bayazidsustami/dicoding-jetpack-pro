package com.dicoding.submission.jetpack.data.dataSource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.submission.jetpack.data.dataSource.local.room.TvShowDao
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

class TvShowLocalDataSourceImpl(
    private val tvShowDao: TvShowDao
): LocalDataSource.TvShowDataSource {
    override fun getListTv(isFavorite: Boolean): DataSource.Factory<Int, TvShowsEntity> {
        return tvShowDao.getListTv(isFavorite)
    }

    override fun getDetailTv(idTv: String): LiveData<DetailTvShowsEntity> {
        return tvShowDao.getDetailTv(idTv)
    }

    override suspend fun insertTvShow(tvShows: List<TvShowsEntity>) {
        tvShowDao.insertTv(tvShows)
    }

    override suspend fun insertDetailTvShow(tvShow: DetailTvShowsEntity) {
        tvShowDao.insertDetailTv(tvShow)
    }
}