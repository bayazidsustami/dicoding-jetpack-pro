package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy

class DetailTvShowViewModel(
    private val repository: TvShowRepository
): ViewModel() {

    private lateinit var tvId: String

    fun setSelectedTvShow(tvId: String){
        this.tvId = tvId
    }

    fun getDetailTvShow() = repository.getDetailTv(tvId)

}