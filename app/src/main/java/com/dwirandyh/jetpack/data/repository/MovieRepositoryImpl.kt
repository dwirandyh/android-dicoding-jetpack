package com.dwirandyh.jetpack.data.repository

import com.dwirandyh.jetpack.data.remote.RemoteDataSource
import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val remote: RemoteDataSource) : MovieRepository {

    override suspend fun getMovie(): Result<ArrayList<MovieModel>> {
        return withContext(Dispatchers.Default) {
            return@withContext Result.Success(remote.getPopularMovie())
        }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetailModel> {
        return withContext(Dispatchers.Default) {
            val movie: MovieDetailModel = remote.getMovieDetail(id)
            return@withContext Result.Success(movie)
        }
    }
}