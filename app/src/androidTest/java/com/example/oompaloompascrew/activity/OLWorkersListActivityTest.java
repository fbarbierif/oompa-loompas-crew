package com.example.oompaloompascrew.activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oompaloompascrew.R;
import com.example.oompaloompascrew.ui.activity.OLWorkersListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class OLWorkersListActivityTest {

    @Rule
    public ActivityScenarioRule<OLWorkersListActivity> activityActivityTestRule =
            new ActivityScenarioRule<>(OLWorkersListActivity.class);

    @Test
    public void TestShowRecyclerView () {
        onView(withId(R.id.rvWorkers)).check(matches(isDisplayed()));
    }

}