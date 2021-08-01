package com.dwirandyh.jetpack.data.remote

import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.model.TvShowModel

interface RemoteDataSource {
    suspend fun getPopularMovie(): ArrayList<MovieModel>
    suspend fun getMovieDetail(id: Int): MovieDetailModel

    suspend fun getPopularTv(): ArrayList<TvShowModel>
    suspend fun getTvDetail(id: Int): TvShowModel
}