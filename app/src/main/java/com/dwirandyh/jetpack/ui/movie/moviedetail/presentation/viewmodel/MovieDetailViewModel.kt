package com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.launch

class MovieDetailViewModel(val repository: MovieRepository) : ViewModel() {

    private var _movie: MutableLiveData<Result<MovieModel>> = MutableLiveData()
    val movie: LiveData<Result<MovieModel>>
        get() = _movie

    fun loadMovie(id: String) {
        _movie.value = Result.Loading

        viewModelScope.launch {
            _movie.value = repository.getMovieDetail(id)
        }
    }
}