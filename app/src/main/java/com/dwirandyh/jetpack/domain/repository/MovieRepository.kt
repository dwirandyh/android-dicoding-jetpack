package com.dwirandyh.jetpack.domain.repository

import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.external.Result

interface MovieRepository {
    suspend fun getMovie() : Result<ArrayList<MovieModel>>
    suspend fun getMovieDetail(id: Int) : Result<MovieDetailModel>
}