package com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwirandyh.jetpack.domain.repository.MovieRepository

class MovieDetailViewModelFactory(val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MovieDetailViewModel(repository = repository) as T
    }

}