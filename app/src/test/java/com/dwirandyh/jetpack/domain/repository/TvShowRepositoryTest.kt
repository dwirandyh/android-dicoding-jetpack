package com.dwirandyh.jetpack.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dwirandyh.jetpack.data.remote.RemoteDataSource
import com.dwirandyh.jetpack.data.repository.TvShowRepositoryImpl
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.TestCoroutineRule
import com.dwirandyh.jetpack.external.convertToDate
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TvShowRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var remote: RemoteDataSource

    private lateinit var repository: TvShowRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = TvShowRepositoryImpl(remote)
    }

    @Test
    fun getMovie() {
        runBlocking {
            // Given
            val tvShowList = ArrayList<TvShowModel>()
            tvShowList.add(
                TvShowModel(
                    1,
                    "titleTest",
                    4.0,
                    "2019-03-19".convertToDate("YYYY-mm-dd"),
                    "https://", "2h 4m",
                    "Lorem ipsum"
                )
            )
            whenever(remote.getPopularTv()).then {
                return@then tvShowList
            }

            // When
            val tvShowListResult = repository.getTvShowList()

            // Then
            verify(remote).getPopularTv()
            assertEquals(tvShowListResult, Result.Success(tvShowList))
        }
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            // Given
            val tvShowDetail = TvShowModel(
                1,
                "titleTest",
                4.0,
                "2019-03-19".convertToDate("YYYY-mm-dd"),
                "https://", "2h 4m",
                "Lorem ipsum"
            )
            whenever(remote.getTvDetail(tvShowDetail.id)).then {
                return@then tvShowDetail
            }

            // When
            val tvShowDetailResult = repository.getTvShowDetail(tvShowDetail.id)

            // Then
            verify(remote).getTvDetail(tvShowDetail.id)
            assertEquals(tvShowDetailResult, Result.Success(tvShowDetail))
        }
    }
}