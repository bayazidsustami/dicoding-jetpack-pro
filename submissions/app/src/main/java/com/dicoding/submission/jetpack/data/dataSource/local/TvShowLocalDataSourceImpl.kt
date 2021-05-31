package com.dicoding.submission.jetpack.data.dataSource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.submission.jetpack.data.dataSource.local.room.TvShowDao
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

class TvShowLocalDataSourceImpl(
    private val tvShowDao: TvShowDao
): LocalDataSource.TvShowDataSource {
    override fun getListTv(): DataSource.Factory<Int, TvShowsEntity> {
        return tvShowDao.getListTv()
    }

    override fun getListFavoriteTv(): DataSource.Factory<Int, TvShowsEntity> {
        return tvShowDao.getListFavoriteTv(true)
    }

    override fun getDetailTv(idTv: String): LiveData<DetailTvShowsEntity> {
        return tvShowDao.getDetailTv(idTv)
    }

    override suspend fun updateTvShow(tvShow: TvShowsEntity) {
        tvShowDao.updateTv(tvShow)
    }

    override suspend fun insertTvShow(tvShows: List<TvShowsEntity>) {
        tvShowDao.insertTv(tvShows)
    }

    override suspend fun insertDetailTvShow(tvShow: DetailTvShowsEntity) {
        tvShowDao.insertDetailTv(tvShow)
    }
}