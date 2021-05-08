package com.csci3130project14.medapp;

import android.view.KeyEvent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.protobuf.Empty;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;



public class DeleteMedEspresso {

    @Rule
    public ActivityScenarioRule<DeleteActivity> activityScenarioRule
            = new ActivityScenarioRule<>(DeleteActivity.class);


    public void getConfirmationPrompt(){
        assertTrue(true);
    }

    public void userConfirmsDeletionYes(){
        assertTrue(true);
    }

    public void userConfirmsDeletionNo(){
        assertFalse(false);
    }
}
