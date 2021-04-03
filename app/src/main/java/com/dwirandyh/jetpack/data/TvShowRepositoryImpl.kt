
package com.dwirandyh.jetpack.data

import com.dwirandyh.jetpack.external.DataDummy
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvShowRepositoryImpl : TvShowRepository {
    override suspend fun getTvShowList(): Result<ArrayList<TvShowModel>> {
        return withContext(Dispatchers.Default) {
            return@withContext Result.Success(DataDummy.generateTvList())
        }
    }

    override suspend fun getTvShowDetail(id: String): Result<TvShowModel> {
        return withContext(Dispatchers.Default) {
            val tvShow = DataDummy.generateTvList().first { it.id == id }
            return@withContext Result.Success(tvShow)
        }
    }
}