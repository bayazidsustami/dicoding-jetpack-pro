package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.utils.Result

class DetailTvShowViewModel(
    private val repository: TvShowRepository
): ViewModel() {

    private var tvId = MutableLiveData<String>()

    fun setSelectedTvShow(tvId: String){
        this.tvId.value = tvId
    }

    val tvDetail: LiveData<Result<DetailTvShowsEntity>> = tvId.switchMap {
        repository.getDetailTv(it)
    }

}