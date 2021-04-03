package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwirandyh.jetpack.domain.repository.TvShowRepository

class TvShowViewModelFactory(val repository: TvShowRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  TvShowViewModel(repository) as T
    }

}