package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy

class DetailTvShowViewModel: ViewModel() {

    private lateinit var tvId: String

    fun setSelectedTvShow(tvId: String){
        this.tvId = tvId
    }

    fun getDetailTvShow(): DetailTvShowsEntity{
        val tvShows = DataDummy.generateDetailTvShow()
        return tvShows.single { it.id == tvId }
    }
}