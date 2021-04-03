package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.domain.repository.TvShowRepository
import com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel.TvShowViewModelFactory
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFragmentTest {

    @Mock
    lateinit var repository: TvShowRepository

    lateinit var fragment: TvShowFragment

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fragment = TvShowFragment().apply {
            viewModelFactory = TvShowViewModelFactory(repository)
        }
    }

    @Test
    fun testTvShowFragmentSuccess() {
        runBlocking {
            // Given
            val tvShowList = ArrayList<TvShowModel>()
            tvShowList.add(
                TvShowModel(
                    "123521",
                    "Tv Show title test)",
                    4.4,
                    "2019-03-11".convertToDate("yyyy-MM-dd"),
                    "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                    "40m",
                    "lorem ipsum"
                )
            )
            val result = Result.Success(tvShowList)
            Mockito.`when`(repository.getTvShowList()).thenReturn(result)

            // When
            launchFragmentInContainer {
                fragment
            }

            // Then
            onView(withId(R.id.tv_recycle_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_recycle_view))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShowList.size))
        }
    }

    @Test
    fun testTvShowFragmentError() {
        runBlocking {
            // Given
            val result = Result.Failure("Falied")
            Mockito.`when`(repository.getTvShowList()).thenReturn(result)

            // When
            launchFragmentInContainer {
                fragment
            }

            // Then
            onView(withId(R.id.loading_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(ViewMatchers.withText("Gagal mengambil data dari server")).inRoot(
                RootMatchers.withDecorView(
                    Matchers.not(
                        fragment.getActivity()?.getWindow()
                            ?.getDecorView()
                    )
                )
            ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun testNavigateToDetail() {
        runBlocking {
            // Given
            val tvShowList = ArrayList<TvShowModel>()
            tvShowList.add(
                TvShowModel(
                    "123521",
                    "Tv Show title test)",
                    4.4,
                    "2019-03-11".convertToDate("yyyy-MM-dd"),
                    "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                    "40m",
                    "lorem ipsum"
                )
            )
            val result = Result.Success(tvShowList)
            Mockito.`when`(repository.getTvShowList()).thenReturn(result)

            // When
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.setGraph(R.navigation.main_navigation)
            navController.setCurrentDestination(R.id.tvFragment)

            launchFragmentInContainer {
                fragment
            }.onFragment { fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
            }

            Thread.sleep(500)

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
}