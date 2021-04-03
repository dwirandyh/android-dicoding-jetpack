package com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.launch

class TvShowDetailViewModel(val repository: TvShowRepository) : ViewModel() {

    private var _tvShow: MutableLiveData<Result<TvShowModel>> = MutableLiveData()
    val tvShow: LiveData<Result<TvShowModel>>
        get() = _tvShow

    fun loadTvShow(id: String) {
        _tvShow.value = Result.Loading

        viewModelScope.launch {
            _tvShow.value = repository.getTvShowDetail(id)
        }
    }
}