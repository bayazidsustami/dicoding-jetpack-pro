package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.Result

class DetailMovieViewModel(
    private val repository: FilmRepository
): ViewModel() {

    private var movieId = MutableLiveData<String>()

    fun setMovieSelected(movieId: String){
        this.movieId.value = movieId
    }

    val detailMovie: LiveData<Result<DetailMovieEntity>> = movieId.switchMap {
        repository.getDetailMovie(it)
    }

    fun setFavorite(movie: MoviesEntity, isFavorite: Boolean){
        repository.updateMovie(movie, isFavorite)
    }

}