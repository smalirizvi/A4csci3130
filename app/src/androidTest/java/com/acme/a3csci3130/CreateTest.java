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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Created by SMAR on 3/15/2018.
 */

public class CreateTest {
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCreate() throws Exception {
        onView(withId(R.id.submitButton)).perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER));
        onView(withId((R.id.bNumber))).perform(click()).perform(typeText("1231242"));
        onView(withId((R.id.name))).perform(click()).perform(typeText("Ali"));
        onView(withId((R.id.address))).perform(click()).perform(typeText("Ali"));
        Espresso.closeSoftKeyboard();
        Thread.sleep(1000);
        onView(withId(R.id.submitButton)).perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER));
    }
}
