package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.utils.Result

class DetailMovieViewModel(
    private val repository: FilmRepository
): ViewModel() {
    private lateinit var movieId:String

    fun setMovieSelected(movieId: String){
        this.movieId = movieId
    }

    fun getDetailMovie(): LiveData<Result<DetailMovieEntity>>
        = repository.getDetailMovie(movieId)

}