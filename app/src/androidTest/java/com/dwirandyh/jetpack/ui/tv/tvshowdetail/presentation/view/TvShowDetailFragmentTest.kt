package com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.view

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.view.MovieDetailFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TvShowDetailFragmentTest {

    lateinit var fragment: TvShowDetailFragment

    @Before
    fun setUp() {
        fragment = TvShowDetailFragment()

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
        launchFragmentInContainer(bundleOf("id" to 84958)) {
            fragment
        }

        // Then
        onView(withId(R.id.tv_tvShow_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_tvShow_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("Loki")))

        onView(withId(R.id.tv_released_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_released_date))
            .check(ViewAssertions.matches(ViewMatchers.withText("14/07/2021")))

        onView(withId(R.id.img_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}