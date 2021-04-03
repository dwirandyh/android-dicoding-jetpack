package com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwirandyh.jetpack.domain.repository.TvShowRepository

class TvShowDetailViewModelFactory(val repository: TvShowRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowDetailViewModel(repository = repository) as T
    }

}