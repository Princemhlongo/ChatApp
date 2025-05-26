package com.mycompany.chatappprogpoe;

import java.io.FileWriter;
import java.io.IOException;

public class Messages {

    private int messageID;
    private String message;
    private String recipient;
    private static int totalMessagesSent = 0;
    private static int messageIdCounter = 1;

    public Messages(String recipient, String message) {
        this.messageID = messageIdCounter++;
        this.message = message;
        this.recipient = recipient;
    }

    // Checks if message length exceeds 250 chars
    public boolean checkMessageLength() {
        return message != null && message.length() > 250;
    }

    // Check messageID max 10 characters
    public boolean checkMessagesID() {
        return Integer.toString(messageID).length() <= 10;
    }

    // Check recipient phone format: starts with + and is 12 chars
    public boolean checkRecipientCell() {
        return recipient != null && recipient.startsWith("+") && recipient.length() == 12;
    }

    // Generate Message Hash
    public String createMessageHash() {
        if (message == null || message.isEmpty()) return "";

        String idStr = String.format("%02d", messageID);
        String[] words = message.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        return (idStr.substring(0, Math.min(2, idStr.length())) + ":" + messageID + ":" + firstWord + lastWord).toUpperCase();
    }

    // Handle send/store/disregard message
    public void sendMessage(String action) {
        switch (action.toLowerCase()) {
            case "send":
                totalMessagesSent++;
                break;
            case "disregard":
                // no action
                break;
            case "store":
                // handled in storeMessage()
                break;
            default:
                System.out.println("Unknown action.");
        }
    }

    // Print all sent messages
    public static String printMessages() {
        return "Total messages sent: " + totalMessagesSent;
    }

    // Return total sent messages count
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Store message to JSON file
    public void storeMessage() {
        String json = "{\n"
                + "  \"messageID\": \"" + messageID + "\",\n"
                + "  \"messageHash\": \"" + createMessageHash() + "\",\n"
                + "  \"recipient\": \"" + recipient + "\",\n"
                + "  \"message\": \"" + (message != null ? message.replace("\"", "\\\"") : "") + "\"\n"
                + "}\n";

        try (FileWriter fw = new FileWriter("storedMessages.json", true)) {
            fw.write(json);
            fw.write("\n");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Methods for unit tests
    public String sentMessages(int option) {
        switch (option) {
            case 1:
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public String getMessagesHash() {
        return createMessageHash();
    }

    // ✅ Getters
    public int getMessageID() {
        return messageID;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }
}