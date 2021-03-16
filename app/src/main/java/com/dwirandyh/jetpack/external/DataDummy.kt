package com.dwirandyh.jetpack.external

import com.dwirandyh.jetpack.movie.domain.model.MovieModel
import kotlinx.coroutines.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

object DataDummy {

    suspend fun generateMovieList(): ArrayList<MovieModel> {
        delay(2000L)

        val movieList = ArrayList<MovieModel>()

        movieList.add(
            MovieModel(
            "M1",
            "Parasite (2019)",
            4.2,
                "2019-01-11".convertToDate("yyyy-MM-dd"),
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
            "2h 40m"
            )
        )

        return movieList
    }
}