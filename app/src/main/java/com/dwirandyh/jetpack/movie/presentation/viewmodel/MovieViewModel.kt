package com.dwirandyh.jetpack.movie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.external.DataDummy
import com.dwirandyh.jetpack.movie.domain.model.MovieModel
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.movie.data.MovieRepositoryImpl
import com.dwirandyh.jetpack.movie.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import kotlin.Exception

class MovieViewModel(
    private val repository: MovieRepository = MovieRepositoryImpl()
): ViewModel() {

    private var _movieList: MutableLiveData<Result<ArrayList<MovieModel>>> = MutableLiveData()
    val movieList: LiveData<Result<ArrayList<MovieModel>>>
        get() = _movieList

    fun loadMovie() {
        _movieList.value = Result.Loading

        viewModelScope.launch {
            _movieList.value = repository.getMovie()
        }
    }
}