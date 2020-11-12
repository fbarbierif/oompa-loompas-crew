package com.example.oompaloompascrew.model;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oompaloompascrew.response.OLWorkersResponse;
import com.example.oompaloompascrew.ui.activity.OLWorkersListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class OLWorkersModelTest {

    private OLWorkersModel olWorkersModel;
    private static final String FIRST_PAGE = "1";
    private static final int PAGE_ITEMS_COUNT = 25;

    @Rule
    public ActivityScenarioRule<OLWorkersListActivity> activityActivityTestRule =
            new ActivityScenarioRule<>(OLWorkersListActivity.class);

    @Before
    public void setUp() {
        olWorkersModel = new OLWorkersModel();
    }

    @Test
    public void TestGetWorkersCompleted() {
        olWorkersModel.getOLWorkersData(FIRST_PAGE).test().assertCompleted();
    }

    @Test
    public void TestGetOLWorkersListCount() {
        final TestSubscriber<OLWorkersResponse> subscriber = TestSubscriber.create();
        olWorkersModel.getOLWorkersData(FIRST_PAGE).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
        assertThat(subscriber.getOnNextEvents().get(0).getOLWorkersList().size(), is(PAGE_ITEMS_COUNT));
    }

}