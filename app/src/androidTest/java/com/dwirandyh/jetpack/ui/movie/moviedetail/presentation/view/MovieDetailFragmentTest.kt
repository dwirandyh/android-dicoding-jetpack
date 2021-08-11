package com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.view

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    lateinit var fragment: MovieDetailFragment

    @Before
    fun setUp() {
        fragment = MovieDetailFragment()

        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun testLoadMovieDetailFragment() {
        // When
        launchFragmentInContainer(bundleOf("id" to 436969)) {
            fragment
        }

        // Then
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(withText("The Suicide Squad")))

        onView(withId(R.id.tv_released_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_released_date)).check(matches(withText("28/07/2021")))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
    }
}