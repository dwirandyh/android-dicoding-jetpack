package com.dwirandyh.jetpack.data

import com.dwirandyh.jetpack.external.DataDummy
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {

    override suspend fun getMovie(): Result<ArrayList<MovieModel>> {
        return withContext(Dispatchers.Default) {
            return@withContext Result.Success(DataDummy.generateMovieList())
        }
    }

    override suspend fun getMovieDetail(id: String): Result<MovieModel> {
        return withContext(Dispatchers.Default) {
            val movie: MovieModel = DataDummy.generateMovieList().first { it.id == id }
            return@withContext Result.Success(movie)
        }
    }
}