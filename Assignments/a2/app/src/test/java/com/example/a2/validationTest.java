package com.example.a2;

import org.junit.Test;
import static org.junit.Assert.*;

public class validationTest {

    @Test
    public void usrnameEmpty(){

        Validation valid = new Validation("","linh@dal.ca");
        Validation v2 = new Validation("Linh","");

        assertTrue("Usrname is an empty string",valid.isEmpty());
        assertFalse("The user is NOT a empty string",v2.isEmpty());
    }

    @Test
    public void validEmailTest(){
        Validation v1 = new Validation("","");
        Validation v2 = new Validation("","linh");
        Validation v3 = new Validation("","linh@");
        Validation v4 = new Validation("","linh@hotmail");
        Validation v5 = new Validation("","linh@hotmail.");
        Validation v6 = new Validation("","linh@hotmail.com");
        Validation v7 = new Validation("Linh","");

        assertFalse("E-mail is empty. Invalid.",v1.validEmail());
        assertFalse("Invalid Email.",v2.validEmail());
        assertFalse("Invalid Email.",v3.validEmail());
        assertFalse("Invalid Email.",v4.validEmail());
        assertFalse("Invalid Email.",v5.validEmail());
        assertTrue("Valid Email.",v6.validEmail());
        assertFalse("Invalid Email." , v7.validEmail());
    }

    @Test
    public void successfulLogin(){
        Validation v1 = new Validation("Linh","ln789013@dal.ca");
        Validation v2 = new Validation("","");
        Validation v3 = new Validation("Linh","");
        Validation v4 = new Validation("","ln789013@dal.ca");

        assertTrue("Successful login.", v1.successLogin());
        assertFalse("Not successful",v2.successLogin());
        assertFalse("Not successful",v3.successLogin());
        assertFalse("Not successful",v4.successLogin());

    }
}
