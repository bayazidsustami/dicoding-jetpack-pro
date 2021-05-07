package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.DataDummy.generateMovieData

class MovieViewModel: ViewModel() {
    fun getMovie():List<MoviesEntity> = generateMovieData()
}