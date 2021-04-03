package com.dwirandyh.jetpack.domain.repository

import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.domain.model.TvShowModel

interface TvShowRepository {
    suspend fun getTvShowList() : Result<ArrayList<TvShowModel>>
    suspend fun getTvShowDetail(id: String) : Result<TvShowModel>
}