package com.dwirandyh.jetpack.data.remote.response.tvdetail

import com.dwirandyh.jetpack.data.remote.response.GenreDataResponse
import com.google.gson.annotations.SerializedName

data class TvDetailResponse(
    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("original_name")
    var originalName: String? = null,

    @field:SerializedName("overview")
    var overview: String? = null,

    @field:SerializedName("vote_average")
    var voteAverage: Double? = null,

    @field:SerializedName("release_date")
    var releaseDate: String? = null,

    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    @field:SerializedName("genres")
    var genreIds: ArrayList<GenreDataResponse>? = null,
)
