package com.dicoding.submission.jetpack.data.dataSource.remote.response.list

import com.google.gson.annotations.SerializedName

data class BaseListResponse<out T>(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<T?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)