package com.dwirandyh.jetpack.data.remote.response.popularmovie

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @field:SerializedName("page")
    var page: Int? = null,

    @field:SerializedName("results")
    var results: List<PopularMovieDataResponse>? = null
)