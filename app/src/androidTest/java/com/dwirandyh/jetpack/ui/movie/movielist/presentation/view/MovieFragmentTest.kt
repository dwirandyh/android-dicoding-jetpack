package com.dwirandyh.jetpack.ui.movie.movielist.presentation.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.contrib.RecyclerViewActions
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.repository.MovieRepository
import com.dwirandyh.jetpack.ui.movie.movielist.presentation.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieFragmentTest {

    @Mock
    lateinit var repository: MovieRepository

    lateinit var fragment: MovieFragment

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fragment = MovieFragment().apply {
            viewModelFactory = MovieViewModelFactory(repository)
        }
    }

    @Test
    fun testMovieFragmentSuccess() {
        runBlocking {
            // Given
            val movieList = ArrayList<MovieModel>()
            movieList.add(
                MovieModel(
                    "131634",
                    "Parasite (2019)",
                    4.2,
                    "2019-01-11".convertToDate("yyyy-MM-dd"),
                    "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                    "2h 40m",
                    "lorem ipsum"
                )
            )
            val result = Result.Success(movieList)
            `when`(repository.getMovie()).thenReturn(result)

            // When
            launchFragmentInContainer {
                fragment
            }

            // Then
            onView(withId(R.id.movie_recycle_view)).check(matches(isDisplayed()))
            onView(withId(R.id.movie_recycle_view)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    movieList.size
                )
            )
        }
    }

    @Test
    fun testMovieFragmentError() {
        runBlocking {
            // Given
            val result = Result.Failure("Falied")
            `when`(repository.getMovie()).thenReturn(result)

            // When
            launchFragmentInContainer {
                fragment
            }

            Thread.sleep(500)

            // Then
            onView(withText("Gagal mengambil data dari server")).inRoot(
                withDecorView(
                    not(
                        fragment.getActivity()?.getWindow()
                            ?.getDecorView()
                    )
                )
            ).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testNavigateToDetail() {
        runBlocking {
            // Given
            val movieList = ArrayList<MovieModel>()
            movieList.add(
                MovieModel(
                    "131634",
                    "Parasite (2019)",
                    4.2,
                    "2019-01-11".convertToDate("yyyy-MM-dd"),
                    "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                    "2h 40m",
                    "lorem ipsum"
                )
            )
            val result = Result.Success(movieList)
            `when`(repository.getMovie()).thenReturn(result)

            // When
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.setGraph(R.navigation.main_navigation)
            navController.setCurrentDestination(R.id.movieFragment)

            launchFragmentInContainer {
                fragment
            }.onFragment { fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
            }

            Thread.sleep(500)

            onView(withId(R.id.movie_recycle_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
            assertThat(navController.currentDestination?.id, `is`(R.id.movieDetailFragment))
        }
    }

}
