package com.csci3130project14.medapp;

public class Validator {
    private String email;
    private String firstName;
    private String lastName;
    private String password;


    //constructor for register
    public Validator(String fName, String lName, String em, String pWord) {
        firstName = fName;
        lastName = lName;
        email = em;
        password = pWord;
    }

    //empty String
    private boolean isEmpty(String target){
        return target.length() == 0;
    }

    public boolean emptyName(String name){
        return isEmpty(name);
    }



    /*Reference: https://www.tutorialspoint.com/how-to-check-email-address-validation-in-android-on-edit-text
    Determine if the email is invalid by the length of the e-mail and the characters in it
     */
    public boolean validEmail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //extended regular expression from the reference above
        if(isEmpty(email)){
            return false;
        }
        else if ((!email.matches(emailPattern))) {
            return false;
        }
        else{
            return true;
        }

    }


    /*At least one capital letter, one lowercase and one uppercase. Got the  regular expression from:
    *https://androidfreetutorial.wordpress.com/2018/01/04/regular-expression-for-password-field-in-android/
    */

    public boolean isValidPassword() {
        final String match = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,10}$";
        if (!passowordLengthCheck()) { //only allowed to have 10 characters, but need at least 5 characters
            return false;
        } else if (!password.matches(match)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean passowordLengthCheck(){
        return password.length() >= 5 && password.length() <= 10;
    }

    //this is used to confirm password
    public boolean samePassword(String password2){
        if(password.equals(password2)){
            return true;
        }
        return false;
    }

    //check whether registration work
    public boolean successfulRegister(){

            return validEmail() && isValidPassword() && !isEmpty(firstName) && !isEmpty(lastName);
    }


    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
