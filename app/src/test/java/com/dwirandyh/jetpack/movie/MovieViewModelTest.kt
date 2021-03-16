package com.dwirandyh.jetpack.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.TestCoroutineRule
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.movie.domain.model.MovieModel
import com.dwirandyh.jetpack.movie.domain.repository.MovieRepository
import com.dwirandyh.jetpack.movie.presentation.viewmodel.MovieViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieListObserver: Observer<Result<ArrayList<MovieModel>>>

    @Captor
    private lateinit var movieListArgumentCaptor: ArgumentCaptor<Result<ArrayList<MovieModel>>>

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(movieRepository)
        viewModel.movieList.observeForever(movieListObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadMovieSuccess() {
        testCoroutineRule.runBlockingTest {
            // Given
            val movieList = ArrayList<MovieModel>()
            movieList.add(MovieModel(
                "idTest",
                "titleTest",
                4.0,
                "2019-03-19".convertToDate("YYYY-mm-dd"),
                "https://", "2h 4m")
            )
            whenever(movieRepository.getMovie()).then {
                return@then Result.Success(movieList)
            }

            // When
            viewModel.loadMovie()

            // Then
            verify(movieListObserver, times(2))
                .onChanged(movieListArgumentCaptor.capture())

            val movieListValues = movieListArgumentCaptor.allValues
            assertEquals(Result.Loading, movieListValues[0])
            assertEquals(Result.Success(movieList), movieListValues[1])
        }
    }
}