package com.dwirandyh.jetpack.ui.movie.movielist.presentation.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieFragmentTest {

    lateinit var fragment: MovieFragment

    @Before
    fun setUp() {
        fragment = MovieFragment()

        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun testMovieFragmentSuccess() {
        // When
        launchFragmentInContainer {
            fragment
        }

        // Then
        onView(withId(R.id.movie_recycle_view)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_recycle_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun testNavigateToDetail() {

        // When
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.main_navigation)
        navController.setCurrentDestination(R.id.movieFragment)

        launchFragmentInContainer {
            fragment
        }.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // THEN
        onView(withId(R.id.movie_recycle_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        assertThat(navController.currentDestination?.id, `is`(R.id.movieDetailFragment))
    }
}
