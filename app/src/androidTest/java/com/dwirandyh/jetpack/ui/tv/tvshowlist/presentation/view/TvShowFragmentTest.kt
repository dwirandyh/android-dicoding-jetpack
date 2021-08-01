package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TvShowFragmentTest {

    lateinit var fragment: TvShowFragment

    @Before
    fun setUp() {
        fragment = TvShowFragment()

        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun testTvShowFragmentSuccess() {
        // When
        launchFragmentInContainer {
            fragment
        }

        // Then
        onView(withId(R.id.tv_recycle_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_recycle_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun testNavigateToDetail() {
        // When
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.main_navigation)
        navController.setCurrentDestination(R.id.tvFragment)

        launchFragmentInContainer {
            fragment
        }.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Then
        onView(withId(R.id.tv_recycle_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        assertThat(
            navController.currentDestination?.id,
            Matchers.`is`(R.id.tvShowDetailFragment)
        )
    }
}