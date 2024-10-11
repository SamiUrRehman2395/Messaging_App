package lab_5;

import java.util.Scanner;
import java.util.Arrays;

public class MessagingSystem {
    private String[][] contacts = new String[100][3];
    private String[][] blocked = new String[100][3];
    private Sms[][] chats = new Sms[100][100];
    private int contactCount = 0;
    private int blockCount = 0;
    private int[] chatCounts = new int[100];
    private final String senderName = "Sami";
    private final String senderNumber = "+923038822180";

     
    {
        preAddedContacts("Imad Wasim", "+923039812367");
        preAddedContacts("Mohammad Amir", "+92317080150");
        preAddedContacts("Shan Masood", "+923457872784");
        preAddedContacts("Salman Ali Agha", "+923210035650");
        preAddedContacts("Fakhar Zaman", "+923002378044");
    }

    public void addContact(String name, String number) {
        contacts[contactCount][0] = Integer.toString(contactCount + 1);
        contacts[contactCount][1] = name;
        contacts[contactCount][2] = number;
        contactCount++;
        System.out.println("Contact added successfully: " + name);
    }


    public void preAddedContacts(String name, String number) {
        contacts[contactCount][0] = Integer.toString(contactCount + 1);
        contacts[contactCount][1] = name;
        contacts[contactCount][2] = number;
        contactCount++;
    }


    private int searchContactIndexByName(String name) {
        for (int i = 0; i < contactCount; i++) {
            if (contacts[i][1].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }


    private int searchBlockIndexByName(String name) {
        for (int i = 0; i < blockCount; i++) {
            if (blocked[i][1].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteContact(String name) {

        int contactIndex = searchContactIndexByName(name);
        if (contactIndex != -1) {
            for (int j = contactIndex; j < contactCount - 1; j++) {
                contacts[j] = contacts[j + 1];
            }
            contactCount--;
            System.out.println("Contact removed.");
        } else {
            System.out.println("No such contact saved.");
        }



    }


    public void viewContacts() {

        if (contactCount == 0) {
            System.out.println("No contacts saved.");
            return;
        }
        System.out.println("Contact List:");
        for (int i = 0; i < contactCount; i++) {
            System.out.println("ID: " + contacts[i][0] + " | Name: " + contacts[i][1] + " | Number: " + contacts[i][2]);
        }
    }

    public void blockContact(String name) {
        int contactIndex = searchContactIndexByName(name);
        if (contactIndex != -1) {

            blocked[blockCount][0] = contacts[contactIndex][0];
            blocked[blockCount][1] = contacts[contactIndex][1];
            blocked[blockCount][2] = contacts[contactIndex][2];

            blockCount++;
            System.out.println(name + " has been blocked.");
        } else {
            System.out.println("No such contact found to block.");
        }
    }

    public void viewBlocked() {
        if (blockCount == 0) {
            System.out.println("No blocked contacts.");
            return;
        }
        System.out.println("Blocked Contacts:");
        for (int i = 0; i < blockCount; i++) {
            System.out.println("Name: " + blocked[i][1] + " | Number: " + blocked[i][2]);
        }
    }

    public void unblockContact(String name) {

        int blockIndex = searchBlockIndexByName(name);
        if (blockIndex != -1) {
            for (int j = blockIndex; j < blockCount - 1; j++) {
                blocked[j] = blocked[j + 1];
            }
            blockCount--;
            System.out.println(name + " has been unblocked.");

        } else {
            System.out.println("No such contact found in block list.");
        }
    }

    public boolean isBlocked(String name) {
        if (searchBlockIndexByName(name) != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void startChat(String name) {

        int contactIndex = searchContactIndexByName(name);
        if (contactIndex != -1) {
            if (isBlocked(name)) {
                System.out.println("The person is blocked.");
                return;
            }
            int contactId = Integer.parseInt(contacts[contactIndex][0]) - 1;

            System.out.println("Chatting with " + contacts[contactIndex][1]);

            System.out.println("Enter '0' to stop chatting.");
            System.out.println("Sender: " + senderName + " | Receiver: " + contacts[contactIndex][1]);


            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();

                if (message.equals("0")) break;


                Sms newMessage = new Sms(message);
                chats[contactId][chatCounts[contactId]++] = newMessage;
                System.out.println("Message sent.");
            }
        } else {
            System.out.println("No such contact found.");
        }

    }

    public void viewChats(String name) {

        int contactIndex = searchContactIndexByName(name);

        if (contactIndex != -1) {
            int contactId = Integer.parseInt(contacts[contactIndex][0]) - 1;
            System.out.println("\nChats with " + contacts[contactIndex][1] + ":");
            System.out.println("Sender: " + senderName + " | Receiver: " + contacts[contactIndex][1]);
            System.out.println("___________________________________________________________________________");


            Sms[] messages = Arrays.copyOf(chats[contactIndex], chatCounts[contactIndex]);

            Arrays.sort(messages, (msg1, msg2) -> msg2.getTimestamp().compareTo(msg1.getTimestamp()));


            for (Sms message : messages) {
                System.out.println(message.toString());

                message.markAsRead();
            }
        } else {
            System.out.println("No chats found with this contact.");
        }


    }



    public void deleteMessage(String name, String messageId) {
        int contactIndex = searchContactIndexByName(name);

        if (contactIndex != -1) {

            int contactId = Integer.parseInt(contacts[contactIndex][0]) - 1;

            for (int j = 0; j < chatCounts[contactId]; j++) {

                if (chats[contactId][j].getMessageId().equals(messageId)) {

                    for (int k = j; k < chatCounts[contactId] - 1; k++) {
                        chats[contactId][k] = chats[contactId][k + 1];
                    }
                    chatCounts[contactId]--;

                    System.out.println("Message deleted.");
                    return;
                }

            }
        }


        System.out.println("No message with the given ID found.");
    }
}
