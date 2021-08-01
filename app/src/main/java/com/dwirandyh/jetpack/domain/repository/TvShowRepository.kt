package com.dwirandyh.jetpack.domain.repository

import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.external.Result

interface TvShowRepository {
    suspend fun getTvShowList() : Result<ArrayList<TvShowModel>>
    suspend fun getTvShowDetail(id: Int) : Result<TvShowModel>
}