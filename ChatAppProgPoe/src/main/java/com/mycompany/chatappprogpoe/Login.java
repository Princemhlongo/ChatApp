/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
/**
 * COMPREHENSIVE JUNIT TESTS FOR CHATAPP - PARTS 1 & 2 Unit Tests for Login and
 * Messages Classes
 *
 * @author Nqobile
 */
 
package com.mycompany.chatappprogpoe;

/**
 * Login class handles user registration, login, and welcome message logic.
 *
 * Date: April 2025
 *
 * Code Attribution:
 *
 * Title: Java Scanner Class Author: W3Schools Date Accessed: 19 April 2025
 * Version: N/A Available: https://www.w3schools.com/java/java_user_input.asp
 *
 * Title: Java Regular Expressions - Java Regex Author: Oracle Date Accessed: 19
 * April 2025 Version: Java SE 8 Available:
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 *
 * Title: How to Validate Phone Numbers in Java Author: Baeldung Date Accessed:
 * 19 April 2025 Version: N/A Available:
 * https://www.baeldung.com/java-regex-validate-phone-numbers
 */
import java.util.Scanner;




/**
 * Login class handles user registration, login, and welcome message logic.
 *
 * Author: Nqobile
 * Date: April 2025
 */

public class Login {
    private String name;
    private String surname;
    private String phoneNumber;
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Login(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String registerUser(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "Registration successful.";
    }

    public boolean checkUserName(String username) {
        if (username == null || username.isEmpty()) return false;
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = password.matches(".[A-Z].");
        boolean hasDigit = password.matches(".\\d.");
        boolean hasSpecial = password.matches(".[!@#$%^&(),.?\":{}|<>].*");

        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        return cellNumber.matches("\\+27\\d{9}");
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.username != null && this.password != null &&
               this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public String returnLoginStatus(String name, String surname, boolean status) {
        if (status) {
            return "Welcome " + name + ", " + surname + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public String regUser(String username, String password, String cellNumber) {
        StringBuilder result = new StringBuilder();

        if (checkUserName(username)) {
            result.append("Username successfully captured\n");
        } else {
            result.append("Username is not correctly formatted\n");
        }

        if (checkPasswordComplexity(password)) {
            result.append("Password successfully captured\n");
        } else {
            result.append("Password is not correctly formatted\n");
        }

        if (checkCellPhoneNumber(cellNumber)) {
            result.append("Cell number successfully captured\n");
        } else {
            result.append("Cell number is not correctly formatted\n");
        }

        if (checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellNumber)) {
            this.username = username;
            this.password = password;
            this.cellPhoneNumber = cellNumber;
        }

        return result.toString();
    }
}