package com.dwirandyh.jetpack.data.remote.service

import com.dwirandyh.jetpack.data.remote.response.moviedetail.MovieDetailResponse
import com.dwirandyh.jetpack.data.remote.response.popularmovie.PopularMovieResponse
import com.dwirandyh.jetpack.data.remote.response.populartv.PopularTvResponse
import com.dwirandyh.jetpack.data.remote.response.tvdetail.TvDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie(): PopularMovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetailResponse

    @GET("tv/popular")
    suspend fun getPopularTv(): PopularTvResponse

    @GET("tv/{id}")
    suspend fun getTvDetail(@Path("id") id: Int): TvDetailResponse
}