package com.dwirandyh.jetpack.data.remote.response.populartv

import com.google.gson.annotations.SerializedName

data class PopularTvResponse(
    @field:SerializedName("page")
    var page: Int? = null,

    @field:SerializedName("results")
    var results: List<PopularTvDataResponse>? = null
)