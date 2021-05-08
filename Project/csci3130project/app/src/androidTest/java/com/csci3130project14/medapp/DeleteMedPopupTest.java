package com.csci3130project14.medapp;

/*import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static android.app.PendingIntent.getActivity;
import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

public class DeleteMedPopupTest {

    @Rule
    public ActivityScenarioRule<viewActivity> activityScenarioRule
            = new ActivityScenarioRule<>(viewActivity.class);

    @Test
    public void testDeletePopup(){
        onView(withId(R.id.goToAddBut))
                .perform(click());
        onView(withId((R.id.medTxt))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("newmed"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addMed))
                .perform(click());
        onView(withId(R.id.backtoView))
                .perform(click());
        onView(withText("newmed"))
                .perform(click());
        onView(withId(R.id.deleteCancel))
                .perform(click());
        onView(withText("newmed"))
                .perform(click());
        onView(withId(R.id.deleteConfirm))
                .perform(click());

    }

}*/
