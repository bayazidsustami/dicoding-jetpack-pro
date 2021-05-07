package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.utils.DataDummy

class TvShowViewModel: ViewModel() {
    fun getListTvShows() = DataDummy.generateListTvShow()
}