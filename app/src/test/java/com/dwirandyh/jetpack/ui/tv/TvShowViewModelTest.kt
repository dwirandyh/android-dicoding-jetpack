package com.dwirandyh.jetpack.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.TestCoroutineRule
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel.TvShowViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var tvShowListObserver: Observer<Result<ArrayList<TvShowModel>>>

    @Captor
    private lateinit var tvShowListArgumentCaptor: ArgumentCaptor<Result<ArrayList<TvShowModel>>>

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowViewModel(tvShowRepository)
        viewModel.tvShowList.observeForever(tvShowListObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadTvShowSuccess() {
        testCoroutineRule.runBlockingTest {
            // Given
            val tvShowList = ArrayList<TvShowModel>()
            tvShowList.add(
                TvShowModel(
                    0,
                    "titleTest",
                    4.0,
                    "2019-03-19".convertToDate("YYYY-mm-dd"),
                    "https://",
                    "2h 4m",
                    "lorem ipsum"
                )
            )
            whenever(tvShowRepository.getTvShowList()).then {
                return@then Result.Success(tvShowList)
            }

            // When
            viewModel.loadTvShow()

            // Then
            verify(tvShowListObserver, times(2))
                .onChanged(tvShowListArgumentCaptor.capture())

            val tvShowListValues = tvShowListArgumentCaptor.allValues
            Assert.assertEquals(Result.Loading, tvShowListValues[0])
            Assert.assertEquals(Result.Success(tvShowList), tvShowListValues[1])
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadTvShowEmpty() {
        testCoroutineRule.runBlockingTest {
            // Given
            val tvShowList = ArrayList<TvShowModel>()
            whenever(tvShowRepository.getTvShowList()).then {
                return@then Result.Success(tvShowList)
            }

            // When
            viewModel.loadTvShow()

            // Then
            verify(tvShowListObserver, times(2))
                .onChanged(tvShowListArgumentCaptor.capture())

            val tvShowListValues = tvShowListArgumentCaptor.allValues
            Assert.assertEquals(Result.Loading, tvShowListValues[0])
            Assert.assertTrue((tvShowListValues[1] as Result.Success<ArrayList<TvShowModel>>).data.isEmpty())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadTvShowError() {
        testCoroutineRule.runBlockingTest {
            val failurResult = Result.Failure("Failed")
            // Given
            whenever(tvShowRepository.getTvShowList()).then {
                return@then failurResult
            }

            // When
            viewModel.loadTvShow()

            // Then
            verify(tvShowListObserver, times(2))
                .onChanged(tvShowListArgumentCaptor.capture())

            val tvShowListValues = tvShowListArgumentCaptor.allValues
            Assert.assertEquals(Result.Loading, tvShowListValues[0])
            Assert.assertEquals(failurResult, tvShowListValues[1])
        }
    }
}