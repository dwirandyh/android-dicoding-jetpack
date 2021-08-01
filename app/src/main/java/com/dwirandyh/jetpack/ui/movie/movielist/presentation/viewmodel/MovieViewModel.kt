package com.dwirandyh.jetpack.ui.movie.movielist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwirandyh.jetpack.data.remote.RemoteDataSourceImpl
import com.dwirandyh.jetpack.data.repository.MovieRepositoryImpl
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import com.dwirandyh.jetpack.external.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel(
    var repository: MovieRepository = MovieRepositoryImpl(RemoteDataSourceImpl())
): ViewModel() {

    private var _movieList: MutableLiveData<Result<ArrayList<MovieModel>>> = MutableLiveData()
    val movieList: LiveData<Result<ArrayList<MovieModel>>>
        get() = _movieList

    fun loadMovie() {
        _movieList.value = Result.Loading


        viewModelScope.launch {
            try {
                _movieList.value = repository.getMovie()
            } catch (e: Exception) {
                _movieList.value = Result.Failure("Gagal memuat data")
            }
        }
    }
}