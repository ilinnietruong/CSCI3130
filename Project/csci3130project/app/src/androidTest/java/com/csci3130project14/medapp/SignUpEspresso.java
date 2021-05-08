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

public class SignUpEspresso {

    @Rule
    public ActivityScenarioRule<Register> activityScenarioRule
            = new ActivityScenarioRule<>(Register.class);

    @Test
    public void testEmptyFields() {
        onView(withId(R.id.signUpButton))
                .perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.firstNameRegister_EditText))
                .check(matches(hasErrorText("Empty. Invalid first name.")));
        onView(withId(R.id.lastNameRegister_EditText))
                .check(matches(hasErrorText("Empty. Invalid last name.")));
        onView(withId(R.id.emailRegister_EditText))
                .check(matches(hasErrorText("Invalid e-mail.Ex:johndoe@dal.ca")));
        onView(withId(R.id.passwordRegister_EditText))
                .check(matches(hasErrorText("Password must be between 5 and 10 characters long.")));
    }

    @Test
    public void testIncorrectEmail(){
        onView(withId(R.id.emailRegister_EditText))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("garbage"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withId(R.id.emailRegister_EditText))
                .check(matches(hasErrorText("Invalid e-mail.Ex:johndoe@dal.ca")));
    }

    @Test
    public void testPasswordShort(){
        onView(withId(R.id.passwordRegister_EditText))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withId(R.id.passwordRegister_EditText))
                .check(matches(hasErrorText("Password must be between 5 and 10 characters long.")));
    }

    @Test
    public void testPasswordLong(){
        onView(withId(R.id.passwordRegister_EditText))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("idsfhuidsugfisuhfuoi"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withId(R.id.passwordRegister_EditText))
                .check(matches(hasErrorText("Password must be between 5 and 10 characters long.")));
    }

    @Test
    public void testPasswordComplexity(){
        onView(withId(R.id.passwordRegister_EditText))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withId(R.id.passwordRegister_EditText))
                .check(matches(hasErrorText("At least one upper case, one lower case, and one integer.")));
    }

    @Test
    public void testPasswordMatch(){
        onView(withId(R.id.passwordRegister_EditText))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("GoodPass5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPasswordRegister_EditText))
                .perform(replaceText(""))
                .perform(click())
                .perform(typeText("GoofPass5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withId(R.id.confirmPasswordRegister_EditText))
                .check(matches(hasErrorText("The password is not the same.")));
    }

}
