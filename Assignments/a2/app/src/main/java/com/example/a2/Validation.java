package com.example.a2;

import android.widget.EditText;


public class Validation {
    private String usrname;
    private String em;

    public Validation(String u, String e) {
        usrname = u;
        em= e;
    }

    //determine whether the input is empty or not.
    boolean isEmpty() {
        if( usrname.length()==0) {
            return true;
        }
        return false;
    }

    /*Reference: https://www.tutorialspoint.com/how-to-check-email-address-validation-in-android-on-edit-text
    Determine if the email is invalid by the length of the e-mail and the characters in it
     */
    boolean validEmail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //extended regular expression from the reference above
        if ((!em.matches(emailPattern))|| em.length()==0) {
            return false;
        }
        return true;
    }

    boolean successLogin() {
        Validation successL = new Validation(usrname,em);
        if (successL.validEmail()== true && successL.isEmpty() == false){
            return true;
        }
        return false;
    }
}

