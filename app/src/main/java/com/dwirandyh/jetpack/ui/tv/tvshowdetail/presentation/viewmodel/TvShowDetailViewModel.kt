package com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class TvShowDetailViewModel(val repository: TvShowRepository) : ViewModel() {

    private var _tvShow: MutableLiveData<Result<TvShowModel>> = MutableLiveData()
    val tvShow: LiveData<Result<TvShowModel>>
        get() = _tvShow

    fun loadTvShow(id: Int) {
        _tvShow.value = Result.Loading

        viewModelScope.launch {
            try {
                _tvShow.value = repository.getTvShowDetail(id)
            } catch (e: Exception) {
                _tvShow.value = Result.Failure("Gagal memuat data")
            }
        }
    }
}