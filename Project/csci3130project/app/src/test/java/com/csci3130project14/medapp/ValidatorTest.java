package com.csci3130project14.medapp;

import android.net.UrlQuerySanitizer;

import org.junit.Test;
import static org.junit.Assert.*;
public class  ValidatorTest {
    private String fName;
    private String lName;
    private String email;
    private String pWord;

    //Testing the isEmpty() method
    @Test
    public void validatorIsEmpty() {
        fName = "John";
        lName = "Doe";
        email = "johndoe1998@gmail.com";
        pWord = "joHn32";

        Validator v1 = new Validator(fName, lName, email, pWord);
        Validator v2 = new Validator("", lName, email, pWord);
        Validator v3 = new Validator(fName, "", email, pWord);
        Validator v4 = new Validator(fName, lName, "", pWord);
        Validator v5 = new Validator(fName, lName, email, "");
        Validator v6 = new Validator("", "", email, pWord);
        Validator v7 = new Validator("", "", "", pWord);
        Validator v8 = new Validator("", lName, "", pWord);
        Validator v9 = new Validator(fName, "", "", pWord);
        Validator v10 = new Validator(fName, lName, "", "");
        Validator v11 = new Validator("", lName, email, "");
        Validator v12 = new Validator(fName, "", email, "");
        Validator v13 = new Validator("", "", "", "");


        boolean allNonEmptyEntries = v1.emptyName(v1.getFirstName())
                && v1.emptyName(v1.getLastName()) && v1.emptyName(v1.getEmail())
                && v1.emptyName(v1.getPassword());
        assertFalse("All strings are NOT empty", allNonEmptyEntries);
        assertTrue("First name is empty", v2.emptyName(v2.getFirstName()));
        assertTrue("last name is empty", v3.emptyName(v3.getLastName()));
        assertTrue("Email is empty", v4.emptyName(v4.getEmail()));
        assertTrue("Password is empty", v5.emptyName(v5.getPassword()));
        assertTrue("First name and last name are empty", (v6.emptyName(v6.getFirstName())
                && v6.emptyName(v6.getFirstName())));
        assertTrue("First name,last name and email are empty", (v7.emptyName(v7.getFirstName())
                && v7.emptyName(v7.getFirstName()) && v7.emptyName(v7.getEmail())));
        assertTrue("First name and e-mail are empty", (v8.emptyName(v8.getFirstName()) && v8.emptyName(v8.getEmail())));
        assertTrue("Last name and email are empty", (v9.emptyName(v9.getLastName()) && v9.emptyName(v9.getEmail())));
        assertTrue("Email and password are empty", (v10.emptyName(v10.getEmail()) && v10.emptyName(v10.getPassword())));
        assertTrue("First name and password are empty", (v11.emptyName(v11.getFirstName()) && v11.emptyName(v11.getPassword())));
        assertTrue("Last name and password are empty", (v12.emptyName(v12.getLastName()) && v12.emptyName(v12.getPassword())));

        boolean allEmptyEntries = v13.emptyName(v13.getFirstName())
                && v13.emptyName(v13.getLastName()) && v13.emptyName(v13.getEmail())
                && v13.emptyName(v13.getPassword());

        assertTrue("All strings are empty", allEmptyEntries);
    }

    //Testing validEmail() method
    @Test
    public void validEmailTest(){
        fName = "";
        lName = "";
        pWord = "";


        Validator v1 = new Validator(fName,lName,"",pWord);
        Validator v2 = new Validator(fName,lName,"johndoe1998",pWord);
        Validator v3 = new Validator(fName,lName,"johndoe1998@",pWord);
        Validator v4 = new Validator(fName,lName,"johndoe1998@gmail",pWord);
        Validator v5 = new Validator(fName,lName,"johndoe1998@gmail.",pWord);
        Validator v6 = new Validator(fName,lName,"johndoe1998@gmail.com",pWord);
        Validator v7 = new Validator(fName,lName,"jon.com",pWord);
        Validator v8 =new Validator(fName,lName,"jon@com",pWord);
        Validator v9 = new Validator(fName,lName,"@hot",pWord);
        Validator v10= new Validator(fName,lName,"@hot.",pWord);
        Validator v11 = new Validator(fName,lName,"@hot.com",pWord);

        assertFalse("E-mail is empty. Invalid.",v1.validEmail());
        assertFalse("Invalid Email.",v2.validEmail());
        assertFalse("Invalid Email.",v3.validEmail());
        assertFalse("Invalid Email.",v4.validEmail());
        assertFalse("Invalid Email.",v5.validEmail());
        assertTrue("Valid Email.",v6.validEmail());
        assertFalse("Invalid Email.",v7.validEmail());
        assertFalse("Invalid Email.",v8.validEmail());
        assertFalse("Invalid Email.",v9.validEmail());
        assertFalse("Invalid Email.",v10.validEmail());
        assertFalse("Invalid Email.",v11.validEmail());





    }


    /*
    This test is no longer needed
     */
    //Testing length of password
    @Test
    public void testPasswordLength(){
        fName = "John";
        lName = "Doe";
        email = "johndoe1998@gmail.com";

        //the length of password must be <=10, and at least >=5
        Validator v1 = new Validator(fName, lName, email, "j");
        Validator v2 = new Validator(fName, lName, email, "jo");
        Validator v3 = new Validator(fName, lName, email, "joh");
        Validator v4 = new Validator(fName, lName, email, "john");
        Validator v5 = new Validator(fName, lName, email, "john1");
        Validator v6 = new Validator(fName, lName, email, "john12");
        Validator v7 = new Validator(fName, lName, email, "john123");
        Validator v8 = new Validator(fName, lName, email, "john1234");
        Validator v9 = new Validator(fName, lName, email, "john12345");
        Validator v10 = new Validator(fName, lName, email, "john123456");
        Validator v11 = new Validator(fName, lName, email, "john1234567");
        Validator v12 = new Validator(fName,lName,email,"");


        assertFalse("There is one character in the password",v1.passowordLengthCheck());
        assertFalse("There are two characters in the password",v2.passowordLengthCheck());
        assertFalse("There are three characters in the password",v3.passowordLengthCheck());
        assertFalse("There are four characters in the password",v4.passowordLengthCheck());
        assertTrue("There are five characters in the password",v5.passowordLengthCheck());
        assertTrue("There are six characters in the password",v6.passowordLengthCheck());
        assertTrue("There are seven characters in the password",v7.passowordLengthCheck());
        assertTrue("There are eight characters in the password",v8.passowordLengthCheck());
        assertTrue("There are nine characters in the password",v9.passowordLengthCheck());
        assertTrue("There are ten characters in the password",v10.passowordLengthCheck());
        assertFalse("There are 11 characters in the password",v11.passowordLengthCheck());
        assertFalse("There is NO character in the password",v12.passowordLengthCheck());

    }
    /*Test whether fits the requirements of the password:
        At least:
            -one uppercase character
            -one lowercase character
            -one digit
     */

    @Test
    public void passwordFit(){
        fName = "John";
        lName = "Doe";
        email = "johndoe1998@gmail.com";



        Validator v1 = new Validator(fName, lName, email, "johndoe");
        Validator v2 = new Validator(fName, lName, email, "JOHNDOE");
        Validator v3 = new Validator(fName, lName, email, "12345678");
        Validator v4 = new Validator(fName, lName, email, "joHJdoe");
        Validator v5 = new Validator(fName, lName, email, "john13");
        Validator v6 = new Validator(fName, lName, email, "JOHN4");
        Validator v7 = new Validator(fName,lName,email,"joHn1");
        Validator v8 = new Validator(fName, lName, email, "jHHJJ3");
        Validator v9 = new Validator(fName, lName, email, "Jhon123");
        Validator v10 = new Validator(fName,lName,email,"5jOnH3");

        assertFalse("Characters are ALL lowercase.",v1.isValidPassword());
        assertFalse("Characters are ALL uppercase.",v2.isValidPassword());
        assertFalse("Characters are ALL digits.",v3.isValidPassword());
        assertFalse("Only lower case and uppercase.",v4.isValidPassword());
        assertFalse("Only lower case and digits.",v5.isValidPassword());
        assertFalse("Only Upper case and digits.",v6.isValidPassword());
        assertTrue("Matches all the requirement and have more than one lowercase.",v7.isValidPassword());
        assertTrue("Matches all the requirement and have more than one uppercase",v8.isValidPassword());
        assertTrue("Matches all the requirement and have more than one digits",v9.isValidPassword());
        assertTrue("Matches all the requirement and have more than one of lowercase,uppercase and digits",v10.isValidPassword());
    }

    //Check if the two passwords the user input are the same
    @Test
    public void samePassword(){
        fName = "";
        lName = "";
        email= "";


        Validator v1 = new Validator(fName,lName,email,"Csci3130");
        Validator v2 = new Validator(fName,lName,email,"Csci3130");
        Validator v3 = new Validator(fName,lName,email,"Csci3130");

        assertTrue(" Same passwords",v1.samePassword("Csci3130"));
        assertFalse("Confirmation password is not the same as the password",v2.samePassword("Csci3136"));
        assertFalse("Password is case sensitive." ,v2.samePassword("CsCi3130"));
    }

    //test whether the registration completed all the requirements
    @Test
    public void registerationComplete(){
        fName = "John";
        lName = "Doe";
        email = "johndoe1998@gmail.com";
        pWord = "joHn32";

        Validator v1= new Validator(fName,lName,email,pWord);
        Validator v2= new Validator("","","","");
        Validator v3 = new Validator(fName,lName,"fnkw","sadsa");
        Validator v4 = new Validator(fName,lName,"susan120@yahoo.","saUN34");
        Validator v5 = new Validator (fName,lName,"susan120@yahoo.com","n");
        Validator v6 = new Validator(fName,lName,"susan12@yahoo.com","nnnnnnnnn");
        Validator v7 = new Validator("",lName,email,pWord);
        Validator v8 = new Validator(fName,"",email,pWord);
        Validator v9 = new Validator("",lName,email,"dundun123");
        Validator v10 = new Validator("",lName,"suspop",pWord);



        assertTrue("Passed all the conditions", v1.successfulRegister());
        assertFalse("All empty. Invalid registeration",v2.successfulRegister());
        assertFalse("Invalid e-mail and password",v3.successfulRegister());
        assertFalse("Invalid email.",v4.successfulRegister());
        assertFalse("Invalid length of the password.",v5.successfulRegister());
        assertFalse("Invalid requirements of the password.",v6.successfulRegister());
        assertFalse("Invalid first name.",v7.successfulRegister());
        assertFalse("Invalid last name.",v8.successfulRegister());
        assertFalse("Invalid first name and invalid password",v9.successfulRegister());
        assertFalse("Invalid first name and invalid e-mail",v10.successfulRegister());


    }
}
