package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.data.repository.TvShowRepositoryImpl
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class TvShowViewModel(
    var showRepository: TvShowRepository
): ViewModel() {

    private var _tvShowShowList: MutableLiveData<Result<ArrayList<TvShowModel>>> = MutableLiveData()
    val tvShowList: LiveData<Result<ArrayList<TvShowModel>>>
        get() = _tvShowShowList

    fun loadTvShow() {
        _tvShowShowList.value = Result.Loading

        viewModelScope.launch {
            try {
                _tvShowShowList.value = showRepository.getTvShowList()
            } catch (e: Exception) {
                _tvShowShowList.value = Result.Failure("Gagal memuat data")
            }
        }
    }
}