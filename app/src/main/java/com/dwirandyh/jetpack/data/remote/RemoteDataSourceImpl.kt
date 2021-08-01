package com.dwirandyh.jetpack.data.remote

import com.dwirandyh.jetpack.data.remote.service.ApiClient
import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.external.Constant
import com.dwirandyh.jetpack.external.convertToDate

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getPopularMovie(): ArrayList<MovieModel> {
        val popularMovie = ApiClient.getApiClient().getPopularMovie().results
        val result: ArrayList<MovieModel> = ArrayList()
        popularMovie?.forEach {
            result.add(
                MovieModel(
                    it.id,
                    it.originalTitle ?: "",
                    it.voteAverage ?: 0.0,
                    (it.releaseDate ?: "0000-00-00").convertToDate("yyyy-MM-dd"),
                    Constant.POSTER_URL + it.posterPath,
                    "",
                    it.overview ?: ""
                )
            )
        }
        return result
    }

    override suspend fun getMovieDetail(id: Int): MovieDetailModel {
        val movieDetail = ApiClient.getApiClient().getMovieDetail(id)
        return MovieDetailModel(
            movieDetail.id,
            movieDetail.originalTitle ?: "",
            movieDetail.voteAverage ?: 0.0,
            (movieDetail.releaseDate ?: "0000-00-00").convertToDate("yyyy-MM-dd"),
            Constant.POSTER_URL + movieDetail.posterPath ?: "",
            "",
            movieDetail.overview ?: "",
            movieDetail.genreIds?.map { it.name }?.toCollection(ArrayList()) ?: ArrayList()
        )
    }

    override suspend fun getPopularTv(): ArrayList<TvShowModel> {
        val popularTv = ApiClient.getApiClient().getPopularTv().results
        val result: ArrayList<TvShowModel> = ArrayList()
        popularTv?.forEach {
            result.add(
                TvShowModel(
                    it.id ?: 0,
                    it.originalName ?: "",
                    it.voteAverage ?: 0.0,
                    (it.releaseDate ?: "0000-00-00").convertToDate("yyyy-MM-dd"),
                    Constant.POSTER_URL + it.posterPath,
                    "",
                    it.overview ?: ""
                )
            )
        }

        return result
    }

    override suspend fun getTvDetail(id: Int): TvShowModel {
        val tvDetail = ApiClient.getApiClient().getTvDetail(id)
        return TvShowModel(
            tvDetail.id ?: 0,
            tvDetail.originalName ?: "",
            tvDetail.voteAverage ?: 0.0,
            (tvDetail.releaseDate ?: "0000-00-00").convertToDate("yyyy-MM-dd"),
            Constant.POSTER_URL + tvDetail.posterPath,
            "",
            tvDetail.overview ?: ""
        )
    }
}