package com.dwirandyh.jetpack.domain.repository

import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.domain.model.MovieModel

interface MovieRepository {
    suspend fun getMovie() : Result<ArrayList<MovieModel>>
    suspend fun getMovieDetail(id: String) : Result<MovieModel>
}