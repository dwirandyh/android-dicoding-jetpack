package com.dwirandyh.jetpack.movie.domain.model

import java.time.LocalDate
import java.util.*

data class MovieModel(
    var id: String,
    var title: String,
    var rating: Double,
    var releaseDate: Date,
    var posterUrl: String,
    var duration: String
)