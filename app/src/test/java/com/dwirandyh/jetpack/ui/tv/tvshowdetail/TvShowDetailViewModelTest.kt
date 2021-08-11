package com.dwirandyh.jetpack.ui.tv.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.TestCoroutineRule
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.viewmodel.TvShowDetailViewModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var tvShowObserver: Observer<Result<TvShowModel>>

    @Captor
    private lateinit var tvShowArgumentCaptor: ArgumentCaptor<Result<TvShowModel>>

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private lateinit var viewModel: TvShowDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowDetailViewModel(tvShowRepository)
        viewModel.tvShow.observeForever(tvShowObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadTvShowDetailSuccess() {
        testCoroutineRule.runBlockingTest {
            // Given
            val tvShow = TvShowModel(
                1,
                "test title",
                5.0,
                "2021-01-01".convertToDate("yyyy-MM-dd"),
                "test poster",
                "test duration",
                "test overview"
            )
            whenever(tvShowRepository.getTvShowDetail(tvShow.id)).then {
                return@then Result.Success(tvShow)
            }

            // When
            viewModel.loadTvShow(tvShow.id)

            // Then
            verify(tvShowRepository).getTvShowDetail(tvShow.id)

            verify(tvShowObserver, VerificationModeFactory.times(2))
                .onChanged(tvShowArgumentCaptor.capture())

            val tvShowValues = tvShowArgumentCaptor.allValues
            Assert.assertEquals(Result.Loading, tvShowValues[0])
            Assert.assertEquals(Result.Success(tvShow), tvShowValues[1])
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadTvShowDetailFailed() {
        testCoroutineRule.runBlockingTest {
            // Given
            whenever(tvShowRepository.getTvShowDetail(1)).then {
                return@then Result.Failure("Failed to load data")
            }

            // When
            viewModel.loadTvShow(1)

            // Then
            verify(tvShowRepository).getTvShowDetail(1)

            verify(tvShowObserver, VerificationModeFactory.times(2))
                .onChanged(tvShowArgumentCaptor.capture())

            val tvShowValues = tvShowArgumentCaptor.allValues
            Assert.assertEquals(Result.Loading, tvShowValues[0])
            Assert.assertEquals(Result.Failure("Failed to load data"), tvShowValues[1])
        }
    }
}