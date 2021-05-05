package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import androidx.lifecycle.ViewModel
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.utils.DataDummy

class DetailMovieViewModel: ViewModel() {
    private lateinit var movieId:String

    fun setMovieSelected(movieId: String){
        this.movieId = movieId
    }

    fun getDetailMovie(): DetailMovieEntity{
        lateinit var movie: DetailMovieEntity
        val moviesData = DataDummy.generateDetailMovie()
        movie = moviesData.single { it.id == movieId }

        return movie
    }
}