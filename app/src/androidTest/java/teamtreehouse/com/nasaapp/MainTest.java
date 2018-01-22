package teamtreehouse.com.nasaapp;

import android.os.Build;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

import static android.app.PendingIntent.getActivity;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    // To-Do, How to click on various datepicker elements, much difficulty correctly selecting a date.
//    @Test
//    public void datePickerRetrievesPhotos() throws Exception {
//
//        boolean isAvailable = false;
//        // Arrange
//
//        while (!isAvailable) {
//            try {
//                onView(allOf(withId(R.id.roverButton), isDisplayed())).perform(click());
//                isAvailable = true;
//            } catch (NoMatchingViewException e) {
//            }
//        }
//        onView(withId(R.id.mdtp_date_picker_year)).perform(click(),swipeLeft());
//    }

    @Test
    public void earthViewReturnsCorrectPhoto() throws Exception {
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
        onView(withId(R.id.dateInput)).perform(typeText(date), closeSoftKeyboard());
        //Sometimes
        onView(withId(R.id.dateInput)).perform(typeText(date), closeSoftKeyboard());
        onView(withId(R.id.addressInput)).perform(typeText(location), closeSoftKeyboard());

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
    }

    @Test
    public void earthViewFailsOnMissingLocationAddress() throws Exception {
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

        onView(withId(R.id.dateInput)).perform(typeText(date), closeSoftKeyboard());
        onView(withId(R.id.dateInput)).perform(typeText(date), closeSoftKeyboard());
        onView(withId(R.id.addressInput)).perform(typeText(location), closeSoftKeyboard());

        onView(withId(R.id.coordinateSubmitButton)).perform(click());
        onView(withText(error)).inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }

    @Test
    public void earthViewFailsOnIncorrectDate() throws Exception {
        boolean isAvailable = false;
        String date = "11-08";
        String location = "pdx";
        String error = "Oops, something went wrong. Check your input!";
        // Arrange

        while (!isAvailable) {
            try {

                onView(allOf(withId(R.id.viewPager))).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft());
                isAvailable = true;

            } catch (NoMatchingViewException e) {
            }
        }

        onView(withId(R.id.dateInput)).perform(typeText(date), closeSoftKeyboard());
        onView(withId(R.id.dateInput)).perform(typeText(date), closeSoftKeyboard());
        onView(withId(R.id.addressInput)).perform(typeText(location), closeSoftKeyboard());

        onView(withId(R.id.coordinateSubmitButton)).perform(click());
        onView(withText(error)).inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }
}
