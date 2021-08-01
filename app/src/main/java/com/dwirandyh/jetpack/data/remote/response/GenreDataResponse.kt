package com.dwirandyh.jetpack.data.remote.response

import com.dwirandyh.jetpack.data.remote.response.popularmovie.PopularMovieDataResponse
import com.google.gson.annotations.SerializedName

data class GenreDataResponse(
    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("name")
    var name: String
)
