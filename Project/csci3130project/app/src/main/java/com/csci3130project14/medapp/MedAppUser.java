package com.csci3130project14.medapp;

import java.util.concurrent.atomic.AtomicInteger;

public class MedAppUser {

    //fields
    private String mUserFirstName;
    private String mUserLastName;
    private String mUserEmail;
    private String mUserPassword;


    /**
     * User constructor
     *
     * @param userFirstName the first name entered by user
     * @param userLastName the last name entered by user
     * @param userEmail the email user uses to register with app
     * @param userPassword the password user uses to register with app
     */
    public MedAppUser(String userFirstName, String userLastName, String userEmail, String userPassword) {
        mUserFirstName = userFirstName;
        mUserLastName = userLastName;
        mUserEmail = userEmail;
        mUserPassword = userPassword;

    }

    /**
     * Default constructor for MedAppUser.
     */
    public MedAppUser(){

    }

    //Getters

    /**
     * Get the first name of user
     *
     * @return user's first name
     */
    public String getUserFirstName() {
        return mUserFirstName;
    }


    /**
     * Get the last name of the user
     *
     * @return user's last name
     */
    public String getUserLastName() {
        return mUserLastName;
    }

    /**
     * Get the user's email
     *
     * @return user's email
     */
    public String getUserEmail() {
        return mUserEmail;
    }


    /**
     * Get the user's password
     *
     * @return user's password
     */
    public String getUserPassword() {
        return mUserPassword;
    }



    //Setters

    /**
     * Set changes to user's first name
     *
     * @param userFirstName the first name to be set
     */
    public void setUserFirstName(String userFirstName) {
        mUserFirstName = userFirstName;
    }


    /**
     * Set changes to user's last name
     *
     * @param userLastName the last name to be set
     */
    public void setUserLastName(String userLastName) {
        mUserLastName = userLastName;
    }


    /**
     * Set changes to user's email (if needed)
     *
     * @param userEmail the email  to be set
     */
    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    /**
     * Set changes to user's password (if needed)
     *
     * @param userPassword the password to be set
     */
    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }


}
