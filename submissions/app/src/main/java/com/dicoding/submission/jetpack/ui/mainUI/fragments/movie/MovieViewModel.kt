package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository

class MovieViewModel constructor(
    val repository: FilmRepository
): ViewModel() {
    fun getMovie() = repository.getDiscoverMovie()
}