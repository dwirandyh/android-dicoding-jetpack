package com.dwirandyh.jetpack.domain.model

import java.util.*

data class TvShowModel(
    var id: String,
    var title: String,
    var rating: Double,
    var releaseDate: Date,
    var posterUrl: String,
    var duration: String,
    var overview: String
)