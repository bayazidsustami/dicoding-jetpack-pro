package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.utils.Result

class TvShowViewModel(
    private val repository: TvShowRepository
): ViewModel() {
    fun getListTvShows(): LiveData<Result<PagedList<TvShowsEntity>>>
        = repository.getDiscoverTv()
}