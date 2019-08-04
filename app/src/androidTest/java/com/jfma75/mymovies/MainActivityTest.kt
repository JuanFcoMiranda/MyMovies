package com.jfma75.mymovies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.jfma75.mymovies.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val searchTerm = "batman"

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        onView(withId(R.id.search_button)).perform(click())

        onView(withId(R.id.search_src_text))
            .perform(replaceText(searchTerm), closeSoftKeyboard())
            .perform(pressImeActionButton())
    }
}