
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
 *
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
 * 
 * /**
 * COMPREHENSIVE JUNIT TESTS FOR CHATAPP - PARTS 1 & 2 Unit Tests for Login and
 * Messages Classes
 *
 * @author Nqobile
 */
 
import com.mycompany.chatappprogpoe.Login;
import com.mycompany.chatappprogpoe.Messages;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * This is the main class that runs the chat app program. It calls methods from
 * the Login class to handle registration, login, and simple messaging.
 */
public class ChatAppProgPoe {

 /**
 * This is the main class that runs the chat app program. It calls methods from
 * the Login class to handle registration and login.
 *
 * Code Attribution:
 *
 * - Java Regular Expressions - Java Regex
 *   Oracle, Java SE 8
 *   https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 *
 * - Java Scanner Class
 *   W3Schools
 *   https://www.w3schools.com/java/java_user_input.asp
 *
 * - How to Validate Phone Numbers in Java
 *   Baeldung
 *   https://www.baeldung.com/java-regex-validate-phone-numbers
 *
 * Author: Nqobile
 */



    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // User registration/login
        String name = JOptionPane.showInputDialog("Enter your name:");
        String surname = JOptionPane.showInputDialog("Enter your surname:");

        Login login = new Login(name, surname, "+27839869876");

        String username = JOptionPane.showInputDialog("Enter a username:");
        String password = JOptionPane.showInputDialog("Enter a password:");
        String cellPhoneNumber = JOptionPane.showInputDialog("Enter cell phone number (format +27xxxxxxxxx):");

        String registrationResult = login.registerUser(username, password, cellPhoneNumber);
        JOptionPane.showMessageDialog(null, registrationResult);

        if (!registrationResult.equals("Registration successful.")) {
            JOptionPane.showMessageDialog(null, "Cannot proceed without successful registration.");
            return;
        }

        // Login prompt
        boolean loggedIn = false;
        while (!loggedIn) {
            String inputUsername = JOptionPane.showInputDialog("Enter your username to login:");
            String inputPassword = JOptionPane.showInputDialog("Enter your password:");

            loggedIn = login.loginUser(inputUsername, inputPassword);

            JOptionPane.showMessageDialog(null, login.returnLoginStatus(name, surname, loggedIn));
            if (!loggedIn) {
                JOptionPane.showMessageDialog(null, "Please try again.");
            }
        }

        // Message handling
        List<Messages> sentMessages = new ArrayList<>();
        int messageCounter = 0;

        boolean quit = false;
        while (!quit) {
            String option = JOptionPane.showInputDialog(
                    "Select an option:\n"
                    + "1) Send Messages\n"
                    + "2) Show recently sent messages\n"
                    + "3) Quit"
            );

            if (option == null) {
                // User pressed cancel
                quit = true;
                continue;
            }

            switch (option) {
                case "1":
                    messageCounter++;
                    String messageText = JOptionPane.showInputDialog("Enter message (max 250 characters):");
                    if (messageText == null) {
                        messageCounter--;
                        break;
                    }

                    Messages message = new Messages(messageText, "Test 2"); // Replace "Test 2" if needed

                    if (message.checkMessageLength()) {
                        JOptionPane.showMessageDialog(null,
                                "Message exceeds 250 characters by " + (messageText.length() - 250) + ", please reduce size.");
                        messageCounter--;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Message ready to send.");
                    }

                    String sendOption = JOptionPane.showInputDialog(
                            "Select option:\n"
                            + "1) Send Message\n"
                            + "2) Disregard Message\n"
                            + "3) Store Message to send later"
                    );

                    if (sendOption == null) {
                        messageCounter--;
                        break;
                    }

                    switch (sendOption) {
                        case "1":
                            message.sendMessage("send");
                            sentMessages.add(message);
                            JOptionPane.showMessageDialog(null,
                                    "Message ID: " + message.getMessageID() + "\n"
                                    + "Message Hash: " + message.createMessageHash() + "\n"
                                    + "Recipient: " + message.getRecipient() + "\n"
                                    + "Message: " + message.getMessage()
                            );
                            JOptionPane.showMessageDialog(null, "Message successfully sent.");
                            break;
                        case "2":
                            message.sendMessage("disregard");
                            JOptionPane.showMessageDialog(null, "Message disregarded.");
                            messageCounter--;
                            break;
                        case "3":
                            message.sendMessage("store");
                            message.storeMessage();
                            JOptionPane.showMessageDialog(null, "Message successfully stored.");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option.");
                            messageCounter--;
                    }
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case "3":
                    quit = true;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option, please select 1, 2, or 3.");
            }
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + sentMessages.size());
    }
}