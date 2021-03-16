package com.dwirandyh.jetpack.movie.domain.repository

import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.movie.domain.model.MovieModel

interface MovieRepository {
    suspend fun getMovie() : Result<ArrayList<MovieModel>>
}