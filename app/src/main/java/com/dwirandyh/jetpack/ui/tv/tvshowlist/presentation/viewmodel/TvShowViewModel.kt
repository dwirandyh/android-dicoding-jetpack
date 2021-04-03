package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.data.TvShowRepositoryImpl
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowViewModel(
    var showRepository: TvShowRepository = TvShowRepositoryImpl()
): ViewModel() {

    private var _tvShowShowList: MutableLiveData<Result<ArrayList<TvShowModel>>> = MutableLiveData()
    val tvShowList: LiveData<Result<ArrayList<TvShowModel>>>
        get() = _tvShowShowList

    fun loadTvShow() {
        _tvShowShowList.value = Result.Loading

        viewModelScope.launch {
            _tvShowShowList.value = showRepository.getTvShowList()
        }
    }
}