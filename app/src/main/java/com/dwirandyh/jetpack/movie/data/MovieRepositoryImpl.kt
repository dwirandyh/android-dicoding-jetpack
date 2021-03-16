package com.dwirandyh.jetpack.movie.data

import com.dwirandyh.jetpack.external.DataDummy
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.movie.domain.model.MovieModel
import com.dwirandyh.jetpack.movie.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {

    override suspend fun getMovie(): Result<ArrayList<MovieModel>> {
        return withContext(Dispatchers.Default) {
            return@withContext Result.Success(DataDummy.generateMovieList())
        }
    }
}