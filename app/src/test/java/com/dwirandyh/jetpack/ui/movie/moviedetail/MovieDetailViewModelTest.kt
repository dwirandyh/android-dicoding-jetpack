package com.dwirandyh.jetpack.ui.movie.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.TestCoroutineRule
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.viewmodel.MovieDetailViewModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieDetailObserver: Observer<Result<MovieDetailModel>>

    @Captor
    private lateinit var movieDetailArgumentCaptor: ArgumentCaptor<Result<MovieDetailModel>>

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieDetailViewModel(movieRepository)
        viewModel.movie.observeForever(movieDetailObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadMovieDetailSuccess() {
        testCoroutineRule.runBlockingTest {
            // Given
            val movieDetail = MovieDetailModel(1, "test title", 5.0, "2021-01-01".convertToDate("yyyy-MM-dd"), "test poster", "test duration", "test overview", arrayListOf())
            whenever(movieRepository.getMovieDetail(movieDetail.id!!)).then {
                return@then Result.Success(movieDetail)
            }

            // When
            viewModel.loadMovie(movieDetail.id!!)

            // Then
            verify(movieRepository).getMovieDetail(movieDetail.id!!)

            verify(movieDetailObserver, times(2))
                .onChanged(movieDetailArgumentCaptor.capture())

            val movieDetailValues = movieDetailArgumentCaptor.allValues
            assertEquals(Result.Loading, movieDetailValues[0])
            assertEquals(Result.Success(movieDetail), movieDetailValues[1])
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadMovieDetailFailed() {
        testCoroutineRule.runBlockingTest {
            // Given
            whenever(movieRepository.getMovieDetail(1)).then {
                return@then Result.Failure("Failed to load data")
            }

            // When
            viewModel.loadMovie(1)

            // Then
            verify(movieRepository).getMovieDetail(1)

            verify(movieDetailObserver, times(2))
                .onChanged(movieDetailArgumentCaptor.capture())

            val movieDetailValues = movieDetailArgumentCaptor.allValues
            assertEquals(Result.Loading, movieDetailValues[0])
            assertEquals(Result.Failure("Failed to load data"), movieDetailValues[1])
        }
    }

}