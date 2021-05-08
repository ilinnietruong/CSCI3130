package com.csci3130project14.medapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class editEspresso {

    @Rule
    public ActivityScenarioRule<add_Activity> activityScenarioRule
            = new ActivityScenarioRule<>(add_Activity.class);

    /*@Test
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


    } */
}
