package com.dwirandyh.jetpack.domain.model

import java.util.*

data class TvShowModel(
    var id: Int,
    var title: String,
    var rating: Double,
    var lastAirDate: Date,
    var posterUrl: String,
    var duration: String,
    var overview: String
)