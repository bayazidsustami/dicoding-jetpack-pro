package com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("iso_639_1")
	val iso6391: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null
)