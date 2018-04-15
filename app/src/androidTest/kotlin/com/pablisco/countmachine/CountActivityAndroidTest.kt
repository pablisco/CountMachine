package com.pablisco.countmachine

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.pablisco.countmachine.count.CountActivity
import org.junit.Rule
import org.junit.Test

class CountActivityAndroidTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(CountActivity::class.java)

    @Test
    fun initialState() {
        onView(withId(R.id.number1Input)).check(matches(withText("")))
        onView(withId(R.id.number2Input)).check(matches(withText("")))
        onView(withId(R.id.number3Input)).check(matches(withText("")))
        onView(withId(R.id.number4Input)).check(matches(withText("")))
        onView(withId(R.id.number5Input)).check(matches(withText("")))
        onView(withId(R.id.number6Input)).check(matches(withText("")))
        onView(withId(R.id.totalView)).check(matches(withText("")))
        onView(withId(R.id.totalDescriptionView)).check(matches(withText("")))
    }

    @Test
    fun shouldRenderOneEntry() {
        onView(withId(R.id.number1Input)).perform(click(), typeText("10"))
        onView(withId(R.id.totalView)).check(matches(withText("10")))
        onView(withId(R.id.totalDescriptionView)).check(matches(withText("")))
    }

    @Test
    fun shouldRenderTwoEntries() {
        onView(withId(R.id.number1Input)).perform(click(), typeText("10"))
        onView(withId(R.id.number2Input)).perform(click(), typeText("20"))
//        onView(withId(R.id.totalView)).perform(click())
        onView(withId(R.id.totalView)).check(matches(withText("30")))
        onView(withId(R.id.totalDescriptionView)).check(matches(withText("10+20=")))
    }

}