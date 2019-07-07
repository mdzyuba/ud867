package com.udacity.gradle.builditbigger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testClickOnTellJokeFromJavaLibButton_showsJoke() {
        onView(withId(R.id.btnTellJoke_javaLib)).perform(click());
        onView(withId(R.id.tvJokeText)).check(matches(withText(containsString("Dad"))));
    }

    @Test
    public void testClickOnTellJokeFromAndroidLibButton_showsJoke() {
        onView(withId(R.id.btnTellJoke_androidLib)).perform(click());
        onView(withId(R.id.tvJokeText)).check(matches(withText(containsString("cake"))));
    }

    @Test
    public void testClickOnTellJokeFromWebLibButton_showsJoke() {
        onView(withId(R.id.btnTellJoke_webApi)).perform(click());
        onView(withId(R.id.tvJokeText)).check(matches(withText(containsString("Teacher"))));
    }
}