package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.utils.DataDummy

class TvShowViewModel(
    private val repository: TvShowRepository
): ViewModel() {
    fun getListTvShows() = repository.getDiscoverTv()
}