package lab_5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sms {
    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;
    private static int idCounter = 1;
    private String messageId;

    public Sms(String content) {
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
        this.messageId = "MSG" + String.format("%03d", idCounter++);
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getMessageId() {
        return messageId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessageStatus() {
        if (isRead) {
            return "Read";
        } else {
            return "Unread";
        }
    }


    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy (HH:mm:ss)");
        String formattedDate = timestamp.format(formatter);

        return "Message: " + content + "\n\nStatus: " + getMessageStatus() +
                "\nDate & Time: " + formattedDate + "\nMessage ID: " + messageId +
                "\n___________________________________________________________________________";
    }
}
