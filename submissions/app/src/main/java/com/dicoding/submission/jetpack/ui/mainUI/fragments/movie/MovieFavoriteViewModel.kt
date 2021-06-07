package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.movie.MoviesEntity

class MovieFavoriteViewModel(
    private val repository: FilmRepository
): ViewModel() {

    fun getMovie(): LiveData<PagedList<MoviesEntity>> = repository.getFavoriteMovie()
}