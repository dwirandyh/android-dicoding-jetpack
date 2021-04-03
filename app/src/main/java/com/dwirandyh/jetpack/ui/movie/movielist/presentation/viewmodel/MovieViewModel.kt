package com.dwirandyh.jetpack.ui.movie.movielist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.data.MovieRepositoryImpl
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    var repository: MovieRepository = MovieRepositoryImpl()
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