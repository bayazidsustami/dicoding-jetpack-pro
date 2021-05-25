package com.dicoding.submission.jetpack.network

import com.dicoding.submission.jetpack.BuildConfig
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.DetailMovieResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("page") page: String = "1",
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseListResponse<ResultsItemMovie>

    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @Query("page") page: String = "1",
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseListResponse<ResultsItemTv>

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") idMovie: String,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): DetailMovieResponse

    @GET("tv/{id}")
    suspend fun getDetailTv(
        @Path("id") idTv: String,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): DetailTvShowResponse
}