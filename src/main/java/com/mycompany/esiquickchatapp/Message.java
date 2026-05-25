/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.esiquickchatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author MakaB
 */
public class Message {
    import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.JSONObject;

/**
 *
 * @author MakaB
 */
public class Messages {

    public static class Message {

        // Declarations
        private String messageID;
        private int messageNumber;
        private String recipient;
        private String messageText;
        private String messageHash;
        private String sendStatus;

        // Static counter — increments automatically each time a message is created
        private static int totalMessages = 0;

        // Default constructor
        public Message() {
        }

        // Parameterized constructor
        public Message(int messageNumber, String recipient, String messageText) {
            this.messageNumber = messageNumber;
            this.recipient     = recipient;
            this.messageText   = messageText;
            this.messageID     = generateMessageID();
            this.messageHash   = createMessageHash();
            totalMessages++;
        }

        /**
         * Generates a random 10-digit message ID, stores it, and returns it.
         * @return 
         */
        public String generateMessageID() {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(10));
            }
            this.messageID = sb.toString();
            return this.messageID;
        }

        /**
         * Checks if the message ID is valid (not more than 10 characters).
         */
        public boolean checkMessageID() {
            return messageID != null && messageID.length() <= 10;
        }

        /**
         * Verifies recipient cell number has an international code and correct length.
         */
        public String checkRecipientCell() {
            if (recipient != null && recipient.matches("^\\+[0-9]{10,12}$")) {
                return "Cell phone number successfully captured.";
            } else {
                return "Cell phone number is incorrectly formatted or does not contain international code.";
            }
        }

        /**
         * Checks that message text does not exceed 250 characters.
         * @return 
         */
        public String checkMessageLength() {
            if (messageText == null || messageText.length() <= 250) {
                return "Message ready to send.";
            } else {
                int over = messageText.length() - 250;
                return "Message exceeds 250 characters by " + over + ", please reduce size.";
            }
        }

        /**
         * Creates and returns the Message Hash.
         * Format: first 2 chars of messageID + ":" + messageNumber + ":" + firstWord + lastWord (ALL CAPS)
         * Example: "00:0:HITONIGHT"
         */
        public String createMessageHash() {
            if (messageID == null || messageText == null) return "";
            String idPart    = messageID.substring(0, 2);
            String[] words   = messageText.trim().split("\\s+");
            String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
            String lastWord  = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
            this.messageHash = (idPart + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
            return this.messageHash;
        }

        /**
         * Handles send / disregard / store based on a String action.
         *
         * @param action "Send", "Disregard", or "Store"
         * @return status message
         */
        public String sentMessage(String action) {
            switch (action) {
                case "Send":
                    this.sendStatus = "Sent";
                    return "Message successfully sent.";
                case "Disregard":
                    this.sendStatus = "Disregarded";
                    return "Press 0 to delete the message.";
                case "Store":
                    this.sendStatus = "Stored";
                    storeMessage();
                    return "Message successfully stored.";
                default:
                    return "Invalid option.";
            }
        }

        /**
         * Returns all message details in required order:
         * Message ID, Message Hash, Recipient, Message.
         * @return 
         */
        public String printMessages() {
            return "Message ID: "   + messageID   + "\n"
                 + "Message Hash: " + messageHash + "\n"
                 + "Recipient: "    + recipient   + "\n"
                 + "Message: "      + messageText;
        }

        /**
         * Returns the total number of messages created so far.
         * @return 
         */
        public int returnTotalMessages() {
            return totalMessages;
        }

        /**
         * Stores message as a JSON entry appended to messages.json.
         */
        public void storeMessage() {
            JSONObject obj = new JSONObject();
            obj.put("messageID",   messageID);
            obj.put("messageHash", messageHash);
            obj.put("recipient",   recipient);
            obj.put("message",     messageText);
            obj.put("status",      sendStatus);
            try (FileWriter file = new FileWriter("messages.json", true)) {
                file.write(obj.toString());
                file.write(System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error occurred while saving: " + e.getMessage());
            }
        }

        /* =========================
           ACCESSORS AND MUTATORS
           ========================= */
        public String getMessageID()   { return messageID; }
        public void setMessageID(String messageID) { this.messageID = messageID; }

        public int getMessageNumber()  { return messageNumber; }
        public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }

        public String getRecipient()   { return recipient; }
        public void setRecipient(String recipient) { this.recipient = recipient; }

        public String getMessageText() { return messageText; }
        public void setMessageText(String messageText) { this.messageText = messageText; }

        public String getMessageHash() { return messageHash; }
        public void setMessageHash(String messageHash) { this.messageHash = messageHash; }

        public String getSendStatus()  { return sendStatus; }
        public void setSendStatus(String sendStatus) { this.sendStatus = sendStatus; }

        public static int getTotalMessages() { return totalMessages; }
        public static void resetTotalMessages() { totalMessages = 0; }
    }
}
    
}
