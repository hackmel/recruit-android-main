package nz.co.test.transactions.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import nz.co.test.transactions.R
import nz.co.test.transactions.activities.customadapter.ListCustomAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun transaction_list_should_be_displayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun detail_fragment_should_be_displayed_when_an_item_is_selected() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<ListCustomAdapter.ViewHolder>(1,click()))
        onView(withId(R.id.detail_summary)).check(matches(withText("Hettinger, Wilkinson and Kshlerin")))
    }

}