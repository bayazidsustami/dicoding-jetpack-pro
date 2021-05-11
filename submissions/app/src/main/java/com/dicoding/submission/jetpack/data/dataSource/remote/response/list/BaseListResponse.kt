package com.dicoding.submission.jetpack.data.dataSource.remote.response.list

import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(

	@field:SerializedName("page")
    var page: Int? = null,

	@field:SerializedName("total_pages")
	var totalPages: Int? = null,

	@field:SerializedName("results")
	var results: List<T?>? = null,

	@field:SerializedName("total_results")
	var totalResults: Int? = null
)