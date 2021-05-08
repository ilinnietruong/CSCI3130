package com.csci3130project14.medapp;

import androidx.test.espresso.Espresso;
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
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

public class LoginEspresso {

    @Rule
    public ActivityScenarioRule<Login> activityScenarioRule
            = new ActivityScenarioRule<>(Login.class);



    @Test
        public void testEmptyFields(){
            onView(withId(R.id.signUpButton))
                    .perform(click());
            onView(withId(R.id.emailButton2))
                    .check(matches(hasErrorText("Empty. Invalid e-mail.")));
            onView(withId(R.id.password))
                    .check(matches(hasErrorText("Empty. Invalid password.")));

    }

    @Test
    public void testInvalidEmail(){

        onView(withId(R.id.emailButton2))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("invalid@garbage.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signUpButton))
                .perform(click());
        //onView(withText("")).inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    //Note: To implement this test, create a test account first in the database.
    @Test
    public void testIncorrectPassword(){
        onView(withId(R.id.emailButton2))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("admin@test.com"));
        onView(withId(R.id.password))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("garbage"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password))
                .check(matches(hasErrorText("Error: Incorrect password.")));
    }



}
