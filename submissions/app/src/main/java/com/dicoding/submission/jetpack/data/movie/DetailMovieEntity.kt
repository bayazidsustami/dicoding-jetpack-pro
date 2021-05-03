package com.dicoding.submission.jetpack.data.movie

import com.dicoding.submission.jetpack.data.ProductionCompanyEntity

data class DetailMovieEntity(
        val homepage: String,
        val title: String,
        val overview: String,
        val productionCompany: List<ProductionCompanyEntity>,
        val status: String,
)