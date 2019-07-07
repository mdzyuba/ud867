package com.udacity.gradle.builditbigger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityFreeTest {

    public static final int WAIT_FOR_AD_MS = 3000;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Thread.sleep(WAIT_FOR_AD_MS);
    }

    @Test
    public void testClickOnTellJokeFromJavaLibButton_showsJoke() throws Exception {
        onView(withId(R.id.btnTellJoke_javaLib)).perform(click());
        closeAdIfDisplayed();
        getInterstitialAdCloseButton().check(doesNotExist());
        onView(withId(R.id.tvJokeText)).check(matches(withText(containsString("cake"))));
    }

    @Test
    public void testClickOnTellJokeFromAndroidLibButton_showsJoke() throws Exception {
        onView(withId(R.id.btnTellJoke_androidLib)).perform(click());
        closeAdIfDisplayed();
        onView(withId(R.id.tvJokeText)).check(matches(withText(containsString("cake"))));
    }

    @Test
    public void testClickOnTellJokeFromWebLibButton_showsJoke() throws Exception {
        onView(withId(R.id.btnTellJoke_webApi)).perform(click());
        closeAdIfDisplayed();
        onView(withId(R.id.tvJokeText)).check(matches(withText(containsString("Teacher"))));
    }

    protected void closeAdIfDisplayed() throws Exception {
        try {
            // If the ad is displayed, press the back button.
            ViewInteraction imageButton = getInterstitialAdCloseButton();
            imageButton.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(isRoot()).perform(pressBack());
        } catch (Exception e) {
            // Ignore. The ad has not been displayed.
        }
    }

    private ViewInteraction getInterstitialAdCloseButton() {
        return onView(
                    allOf(
                        withClassName(containsString("ImageButton")),
                        withContentDescription(containsString("close"))));
    }

}