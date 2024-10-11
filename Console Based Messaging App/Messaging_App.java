package lab_5;

import java.util.Scanner;

public class Messaging_App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MessagingSystem messagingSystem = new MessagingSystem();

        int failedAttempts = 0;

        while (true) {
            System.out.println("Enter password: ");
            String password = scanner.nextLine();

            if (password.equals("2395")) {


                while (true) {
                    System.out.println("""
                            -----------------------
                                 CHIT CHAT HUB
                            -----------------------
                            1. Add Contact
                            2. View Contacts List
                            3. Delete Contact
                            4. Block Contact
                            5. View Blocked Contacts
                            6. Unblock Contact
                            7. Start Chat
                            8. View Chats
                            9. Delete Message
                            0. Exit
                            """);
                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter contact name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter contact number: ");
                            String number = scanner.nextLine();
                            messagingSystem.addContact(name, number);
                            break;

                        case 2:
                            messagingSystem.viewContacts();
                            break;

                        case 3:
                            System.out.print("Enter the contact name to delete: ");
                            name = scanner.nextLine();
                            messagingSystem.deleteContact(name);
                            break;

                        case 4:
                            System.out.print("Enter the contact name to block: ");
                            name = scanner.nextLine();
                            messagingSystem.blockContact(name);
                            break;

                        case 5:
                            messagingSystem.viewBlocked();
                            break;

                        case 6:
                            System.out.print("Enter the contact name to unblock: ");
                            name = scanner.nextLine();
                            messagingSystem.unblockContact(name);
                            break;

                        case 7:
                            System.out.print("Enter the contact name to start chat: ");
                            name = scanner.nextLine();
                            messagingSystem.startChat(name);
                            break;

                        case 8:
                            System.out.print("Enter the contact name to view chats: ");
                            name = scanner.nextLine();
                            messagingSystem.viewChats(name);
                            break;

                        case 9:
                            System.out.print("Enter the contact name to delete message: ");
                            name = scanner.nextLine();
                            System.out.print("Enter the message ID to delete: ");
                            String messageId = scanner.nextLine();
                            messagingSystem.deleteMessage(name, messageId);
                            break;

                        case 0:
                            System.out.println("Exiting the app.");
                            return;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                failedAttempts++;
                if (failedAttempts >= 3) {
                    System.out.println("\nYou've reached the maximum limit to try password. Try again after some time.");
                    break;
                } else {
                    System.out.println("Invalid Password. Try Again\n");
                }
            }

        }
    }
}
