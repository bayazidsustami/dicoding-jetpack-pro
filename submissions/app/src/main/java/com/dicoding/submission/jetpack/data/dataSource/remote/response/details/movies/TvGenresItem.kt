package com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies

import com.google.gson.annotations.SerializedName

data class TvGenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)