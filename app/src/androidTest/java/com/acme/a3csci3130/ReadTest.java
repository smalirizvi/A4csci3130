package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by SMAR on 3/15/2018.
 */

public class ReadTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRead() throws Exception {
        Thread.sleep(1000);
        onView(withId(R.id.listView));
        // Must match the fields of the first item in the db
        onData(anything()).inAdapterView(allOf(withId(R.id.listView), isCompletelyDisplayed())).atPosition(0).perform(click());
        // Check if fields have values
        onView(withId((R.id.bNumber))).check(matches(withText("1231242")));
        onView(withId((R.id.name))).check(matches(withText("Ali")));
        onView(withId(R.id.primaryB)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.primaryB)).check(matches(withSpinnerText(containsString("Fisher"))));
        onView(withId(R.id.province)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.province)).check(matches(withSpinnerText(containsString("AB"))));
    }
}
