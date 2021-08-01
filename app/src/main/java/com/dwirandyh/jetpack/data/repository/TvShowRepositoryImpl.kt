
package com.dwirandyh.jetpack.data.repository

import com.dwirandyh.jetpack.data.remote.RemoteDataSourceImpl
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvShowRepositoryImpl(private val remote: RemoteDataSourceImpl) : TvShowRepository {
    override suspend fun getTvShowList(): Result<ArrayList<TvShowModel>> {
        return withContext(Dispatchers.Default) {
            return@withContext Result.Success(remote.getPopularTv())
        }
    }

    override suspend fun getTvShowDetail(id: Int): Result<TvShowModel> {
        return withContext(Dispatchers.Default) {
            val tvShow = remote.getTvDetail(id)
            return@withContext Result.Success(tvShow)
        }
    }
}