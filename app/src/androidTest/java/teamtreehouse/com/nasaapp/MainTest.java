package teamtreehouse.com.nasaapp;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParentIndex;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainTest {



    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void curiousityApiCallRetrievesCorrectPhotos() throws Exception {
        int counter = 0;
        boolean isAvailable = false;
        boolean earthPhotoAvail = false;
        String date = "2015-11-08";
        String location = "pdx";
        String expectedUri = "https://earthengine.googleapis.com/api/thumb?thumbid=9c8d7b0c8c77a3f6d6d3cd84baa133aa";
        // Arrange

        while (!isAvailable) {
            try {

                onView(allOf(withId(R.id.viewPager))).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft());
                isAvailable = true;

            } catch (NoMatchingViewException e) {
            }
        }
        onView(withId(R.id.dateInput)).perform(typeText(date),closeSoftKeyboard());
        //Sometimes
        onView(withId(R.id.dateInput)).perform(typeText(date),closeSoftKeyboard());
        onView(withId(R.id.addressInput)).perform(typeText(location),closeSoftKeyboard());

        onView(withId(R.id.coordinateSubmitButton)).perform(click());
        int i = 0;
        while (!earthPhotoAvail) {
            try {
                Thread.sleep(5000);
                i++;
                onView(withText(expectedUri)).check(matches(withText(expectedUri)));
                earthPhotoAvail = true;
            } catch (NoMatchingViewException e) {

            }
        }
        // Assert

//        intended(hasComponent(MainActivity.class.getName()));
//        Intents.release();
    }

    @Test
    public void curiousityApiCallNoCoordinatesFound() throws Exception {
        int counter = 0;
        boolean isAvailable = false;
        boolean earthPhotoAvail = false;
        String date = "2015-11-08";
        String location = "";
        String error = "Oops, something went wrong. Check your input!";
        // Arrange

        while (!isAvailable) {
            try {

                onView(allOf(withId(R.id.viewPager))).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft());
                isAvailable = true;

            } catch (NoMatchingViewException e) {
            }
        }
        onView(withId(R.id.dateInput)).perform(typeText(date),closeSoftKeyboard());
        //Sometimes
        onView(withId(R.id.dateInput)).perform(typeText(date),closeSoftKeyboard());
        onView(withId(R.id.addressInput)).perform(typeText(location),closeSoftKeyboard());

        onView(withId(R.id.coordinateSubmitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText(error)).check(matches(isDisplayed()));
        onView(withText(error)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

//
//    @Test
//    public void incorrectCredentialsDisplaysDialog() throws Exception {
//
//        // Arrange
//
//        String userName = "ben";
//        String password = "wrongtest";
//        onView(withId(R.id.usernameField)).perform(typeText(userName));
//        onView(withId(R.id.passwordField)).perform(typeText(password));
//
//        // Act
//        onView(withId(R.id.loginButton)).perform(click());
//
//        // Assert
//
//        onView(withText("Try logging in again with the required credentials or sign up.")).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void missingUsernameDisplaysDialog() throws Exception {
//
//        // Arrange
//
//        String password = "test";
//        onView(withId(R.id.passwordField)).perform(typeText(password));
//
//        // Act
//        onView(withId(R.id.loginButton)).perform(click());
//
//        // Assert
//
//        onView(withText(R.string.login_error_message)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void missingPasswordDisplaysDialog() throws Exception {
//
//        // Arrange
//
//        String userName = "ben";
//        onView(withId(R.id.usernameField)).perform(typeText(userName));
//
//        // Act
//        onView(withId(R.id.loginButton)).perform(click());
//
//        // Assert
//
//        onView(withText("Try logging in again with the required credentials or sign up.")).check(matches(isDisplayed()));
//    }
}
