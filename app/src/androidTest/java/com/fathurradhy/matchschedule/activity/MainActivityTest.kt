package com.fathurradhy.matchschedule.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.fathurradhy.matchschedule.R.id.*
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.fathurradhy.matchschedule.utils.EspressoIdlingResource
import android.support.test.espresso.IdlingRegistry
import org.junit.After
import org.junit.Before



@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        //MAINACTIVITY
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        onView(allOf(withId(rv_team_prev))).check(matches(isDisplayed()))
        onView(withText("NEXT MATCH")).perform(click())
        onView(withId(rv_team_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(withId(rv_team_next)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)

        //DETAILMATCHACTIVITY
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        onView(withId(home_logo)).check(matches(isDisplayed()))
        onView(withId(away_logo)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)

        pressBack()
    }
}