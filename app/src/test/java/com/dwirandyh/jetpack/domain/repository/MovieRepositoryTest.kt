package com.dwirandyh.jetpack.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dwirandyh.jetpack.data.remote.RemoteDataSource
import com.dwirandyh.jetpack.data.repository.MovieRepositoryImpl
import com.dwirandyh.jetpack.domain.model.MovieDetailModel
import com.dwirandyh.jetpack.domain.model.MovieModel
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
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var remote: RemoteDataSource

    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MovieRepositoryImpl(remote)
    }

    @Test
    fun getMovie() {
        runBlocking {
            // Given
            val movieList = ArrayList<MovieModel>()
            movieList.add(
                MovieModel(
                    1,
                    "titleTest",
                    4.0,
                    "2019-03-19".convertToDate("YYYY-mm-dd"),
                    "https://", "2h 4m",
                    "Lorem ipsum"
                )
            )
            whenever(remote.getPopularMovie()).then {
                return@then movieList
            }

            // When
            val movieListResult = repository.getMovie()

            // Then
            verify(remote).getPopularMovie()
            assertEquals(movieListResult, Result.Success(movieList))
        }
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            // Given
            val movieDetail = MovieDetailModel(1, "test title", 5.0, "2021-01-01".convertToDate("yyyy-MM-dd"), "test Poster", "test duration", "test overview", arrayListOf())
            whenever(remote.getMovieDetail(movieDetail.id!!)).then {
                return@then movieDetail
            }

            // When
            val movieDetailResult = repository.getMovieDetail(movieDetail.id!!)

            // Then
            verify(remote).getMovieDetail(movieDetail.id!!)
            assertEquals(movieDetailResult, Result.Success(movieDetail))
        }
    }
}