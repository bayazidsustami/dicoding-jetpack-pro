package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository

class TvShowViewModel(
    private val repository: TvShowRepository
): ViewModel() {
    fun getListTvShows() = repository.getDiscoverTv()
}