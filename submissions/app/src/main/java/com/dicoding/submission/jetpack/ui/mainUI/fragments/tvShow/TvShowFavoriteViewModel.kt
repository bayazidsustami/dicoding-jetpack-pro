package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

class TvShowFavoriteViewModel(
    private val repository: TvShowRepository
): ViewModel() {

    fun getMovie(): LiveData<PagedList<TvShowsEntity>> = repository.getFavoriteTvShow()
}