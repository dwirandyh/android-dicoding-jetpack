package com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(val repository: MovieRepository) : ViewModel() {

    private var _movie: MutableLiveData<Result<MovieDetailModel>> = MutableLiveData()
    val movie: LiveData<Result<MovieDetailModel>>
        get() = _movie

    fun loadMovie(id: Int) {
        _movie.value = Result.Loading

        viewModelScope.launch {
            try {
                _movie.value = repository.getMovieDetail(id)
            } catch (e: Exception) {
                _movie.value = Result.Failure("Gagal memuat data")
            }
        }
    }
}