package com.dwirandyh.jetpack.domain.model

import java.util.*
import kotlin.collections.ArrayList

data class MovieDetailModel(
    var id: Int?,
    var title: String,
    var rating: Double,
    var releaseDate: Date,
    var posterUrl: String,
    var duration: String,
    var overview: String,
    var genres: ArrayList<String>
) {
    val formattedGenre: String
    get() = genres.joinToString(separator = ", ")
}
