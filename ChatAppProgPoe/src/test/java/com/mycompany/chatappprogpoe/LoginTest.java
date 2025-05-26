/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


/**
 * This is the main class that runs the chat app program. It calls methods from
 * the Login class to handle registration and login.
 *
 * Code Attribution:
 *
 * Title: Java Regular Expressions - Java Regex
 *
 * Author: Oracle
 *
 * Date Accessed: 19 April 2025
 *
 * Version: Java SE 8
 *
 * Available:
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 *
 *
 *
 * Title: Java Scanner Class
 *
 * Author: W3Schools
 *
 * Date Accessed: 19 April 2025
 *
 * Version: N/A
 *
 * Available: https://www.w3schools.com/java/java_user_input.asp
 *
 *
 *
 * Title: How to Validate Phone Numbers in Java
 *
 * Author: Baeldung
 *
 * Date Accessed: 19 April 2025
 *
 * Version: N/A
 *
 * Available: https://www.baeldung.com/java-regex-validate-phone-numbers
 */

package com.mycompany.chatappprogpoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

/**
 * COMPREHENSIVE JUNIT TESTS FOR CHATAPP - PARTS 1 & 2 Unit Tests for Login and
 * Messages Classes
 *
 * @author Nqobile
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {

    }

    // =============================================================================
    // LOGIN CLASS TESTS
    // =============================================================================
    @Nested
    @DisplayName("Username Validation Tests")
    class UsernameValidationTests {

        @Test
        @DisplayName("Valid usernames should pass validation")
        public void testValidUsernames() {
            assertTrue(login.checkUserName("kyl_1"), "Username 'kyl_1' should be valid");
            assertTrue(login.checkUserName("Ky_L1"), "Username 'Ky_L1' should be valid");
            assertTrue(login.checkUserName("a_b"), "Username 'a_b' should be valid");
            assertTrue(login.checkUserName("x_"), "Username 'x_' should be valid");
            assertTrue(login.checkUserName("_test"), "Username '_test' should be valid");
        }

        @Test
        @DisplayName("Invalid usernames should fail validation")
        public void testInvalidUsernames() {
            // Too long (more than 5 characters)
            assertFalse(login.checkUserName("kyle_123"), "Username 'kyle_123' should be invalid (too long)");
            assertFalse(login.checkUserName("long_user"), "Username 'long_user' should be invalid (too long)");

            // No underscore
            assertFalse(login.checkUserName("kyle1"), "Username 'kyle1' should be invalid (no underscore)");
            assertFalse(login.checkUserName("kyle"), "Username 'kyle' should be invalid (no underscore)");

            // Special characters other than underscore
            assertFalse(login.checkUserName("kyle!!!!!!!"), "Username with special chars should be invalid");

            // Null and empty cases
            assertFalse(login.checkUserName(null), "Null username should be invalid");
            assertFalse(login.checkUserName(""), "Empty username should be invalid");
        }
    }

    @Nested
    @DisplayName("Password Complexity Tests")
    class PasswordComplexityTests {

        @Test
        @DisplayName("Valid complex passwords should pass validation")
        public void testValidPasswords() {
            assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"),
                    "Password 'Ch&&sec@ke99!' should be valid");
            assertTrue(login.checkPasswordComplexity("MyPass123!"),
                    "Password 'MyPass123!' should be valid");
            assertTrue(login.checkPasswordComplexity("SecureP@ss1"),
                    "Password 'SecureP@ss1' should be valid");
        }

        @Test
        @DisplayName("Invalid passwords should fail validation")
        public void testInvalidPasswords() {
            // Simple password
            assertFalse(login.checkPasswordComplexity("password"),
                    "Password 'password' should be invalid");

            // Too short
            assertFalse(login.checkPasswordComplexity("Pass1!"),
                    "Password 'Pass1!' should be invalid (too short)");

            // Missing uppercase
            assertFalse(login.checkPasswordComplexity("password123!"),
                    "Password without uppercase should be invalid");

            // Missing lowercase
            assertFalse(login.checkPasswordComplexity("PASSWORD123!"),
                    "Password without lowercase should be invalid");

            // Missing digit
            assertFalse(login.checkPasswordComplexity("Password!"),
                    "Password without digit should be invalid");

            // Missing special character
            assertFalse(login.checkPasswordComplexity("Password123"),
                    "Password without special char should be invalid");
        }
    }

    @Nested
    @DisplayName("Cell Phone Number Validation Tests")
    class CellPhoneValidationTests {

        @Test
        @DisplayName("Valid cell phone numbers should pass validation")
        public void testValidCellNumbers() {
            assertTrue(login.checkCellPhoneNumber("+27838968976"),
                    "Valid SA cell number should pass");
            assertTrue(login.checkCellPhoneNumber("+27718693002"),
                    "Another valid SA cell number should pass");
            assertTrue(login.checkCellPhoneNumber("+27823344556"),
                    "084 prefix should be valid");
        }

        @Test
        @DisplayName("Invalid cell phone numbers should fail validation")
        public void testInvalidCellNumbers() {
            assertFalse(login.checkCellPhoneNumber("08966553"),
                    "Short number should be invalid");
            assertFalse(login.checkCellPhoneNumber("0823344556"),
                    "Number without +27 should be invalid");
            assertFalse(login.checkCellPhoneNumber("1234567890"),
                    "Random number should be invalid");
        }
    }

    @Nested
    @DisplayName("User Registration Tests")
    class UserRegistrationTests {

        @Test
        @DisplayName("Valid registration should succeed")
        public void testValidRegistration() {
            String result = login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");
            assertTrue(result.contains("Username successfully captured"),
                    "Valid registration should capture username");
            assertTrue(result.contains("Password successfully captured"),
                    "Valid registration should capture password");
            assertTrue(result.contains("Cell number successfully captured"),
                    "Valid registration should capture cell number");
        }

        @Test
        @DisplayName("Invalid username registration should fail")
        public void testInvalidUsernameRegistration() {
            String result = login.regUser("kyle", "Ch&&sec@ke99!", "+27838968976");
            assertTrue(result.contains("Username is not correctly formatted"),
                    "Invalid username should show error message");
        }
    }

    @Nested
    @DisplayName("User Login Tests")
    class UserLoginTests {

        @Test
        @DisplayName("Valid login should succeed")
        public void testValidLogin() {
            // First register a user
            login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");

            // Then test login
            assertTrue(login.loginUser("ky_l1", "Ch&&sec@ke99!"),
                    "Valid credentials should allow login");
        }

        @Test
        @DisplayName("Invalid login should fail")
        public void testInvalidLogin() {
            // Register a user first
            login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");

            // Test with wrong credentials
            assertFalse(login.loginUser("wrong", "pass123"),
                    "Invalid credentials should fail login");
        }
    }

    @Nested
    @DisplayName("Login Status Tests")
    class LoginStatusTests {

        @Test
        @DisplayName("Successful login status should show welcome message")
        public void testSuccessfulLoginStatus() {
            String expected = "Welcome Kyle Smith, it is great to see you again.";
            String actual = login.returnLoginStatus("Kyle", "Smith", true);
            assertEquals(expected, actual, "Successful login should show welcome message");
        }

        @Test
        @DisplayName("Failed login status should show error message")
        public void testFailedLoginStatus() {
            String expected = "Username or password incorrect, please try again.";
            String actual = login.returnLoginStatus("Kyle", "Smith", false);
            assertEquals(expected, actual, "Failed login should show error message");
        }
    }

    // =============================================================================
    // MESSAGES CLASS TESTS
    // =============================================================================
    @Nested
    @DisplayName("Messages Class Tests")
    class MessagesClassTests {

        private Messages message;

        @BeforeEach
        public void setUpMessage() {
            message = new Messages("+27718693002", "Dinner tonight?");
        }

        @Test
        @DisplayName("Message ID validation should work correctly")
        public void testMessageIdValidation() {
            assertTrue(message.checkMessagesID(), "Valid message ID should pass validation");
        }

        @Test
        @DisplayName("Valid recipient cell numbers should pass validation")
        public void testValidRecipientCell() {
            Messages validMessage = new Messages("+27718693002", "Test message");
            assertTrue(validMessage.checkRecipientCell(), "Valid SA cell number should pass");
        }

        @Test
        @DisplayName("Invalid recipient cell numbers should fail validation")
        public void testInvalidRecipientCell() {
            Messages invalidMessage = new Messages("0823344556", "Test message");
            assertFalse(invalidMessage.checkRecipientCell(), "Invalid cell format should fail");
        }

        @Test
        @DisplayName("Message hash should be properly formatted")
        public void testMessageHashFormat() {
            Messages msg = new Messages("+27718693002", "Hi Mike, can you join us for dinner tonight");
            String hash = msg.getMessagesHash();
            // Check if hash follows expected pattern (adjust pattern based on your implementation)
            assertNotNull(hash, "Message hash should not be null");
            assertTrue(hash.length() > 0, "Message hash should not be empty");
        }

        @Test
        @DisplayName("Send message option 1 should work correctly")
        public void testSendMessageOption1() {
            Messages msg = new Messages("+27718693002", "Hi Mike");
            String response = msg.sentMessages(1);
            assertEquals("Message successfully sent.", response, "Option 1 should send message");
        }

        @Test
        @DisplayName("Send message option 2 should work correctly")
        public void testSendMessageOption2() {
            Messages msg = new Messages("+27718693002", "Hi Keegan");
            String response = msg.sentMessages(2);
            assertEquals("Press 0 to delete message.", response, "Option 2 should show delete option");
        }

        @Test
        @DisplayName("Send message option 3 should work correctly")
        public void testSendMessageOption3() {
            Messages msg = new Messages("+27718693002", "Store this");
            String response = msg.sentMessages(3);
            assertEquals("Message successfully stored.", response, "Option 3 should store message");
        }

        @Test
        @DisplayName("Invalid send message option should show error")
        public void testInvalidSendMessageOption() {
            Messages msg = new Messages("+27718693002", "Whatever");
            String response = msg.sentMessages(99);
            assertEquals("Invalid option.", response, "Invalid option should show error");
        }

        @Test
        @DisplayName("Total message count should increment correctly")
        public void testTotalMessageCount() {
            int initialCount = Messages.returnTotalMessages();
            new Messages("+27718693002", "Test 1");
            new Messages("+27718693002", "Test 2");
            assertEquals(initialCount + 2, Messages.returnTotalMessages(),
                    "Message count should increment by 2");
        }
    }
}
