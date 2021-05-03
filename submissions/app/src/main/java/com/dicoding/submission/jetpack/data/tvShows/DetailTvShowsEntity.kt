package com.dicoding.submission.jetpack.data.tvShows

import com.dicoding.submission.jetpack.data.ProductionCompanyEntity

data class DetailTvShowsEntity(
        val homepage: String,
        val title: String,
        val overview: String,
        val status: String,
        val listCompany: List<ProductionCompanyEntity>,
        val episodeCount: String
)
