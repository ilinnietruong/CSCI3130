package com.csci3130project14.medapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;

/*public class AddMedEspresso {

    @Rule
    public ActivityScenarioRule<Add> activityScenarioRule
            = new ActivityScenarioRule<>(Add.class);

    @Test
    public void testEmptyInfo(){
        onView(withId(R.id.add))
                .perform(click());
        onView(withId(R.id.medName))
                .check(matches(hasErrorText("Empty. Please include medication name.")));
    }

    @Test
    public void testAdd(){
        onView(withId(R.id.medName))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("Medistuff"));
        onView(withId(R.id.add))
                .perform(click());
        onData(withId(R.id.medAddList))
                .atPosition(0).check(matches(withText("Medistuff")));


    }

}*/
