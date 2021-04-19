package io.email.test.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;

public class EmailContentUtil {

    public static String getContentOfEmail(Message message) throws MessagingException, IOException {
        String contentType = message.getContentType();
        String messageContent ="";

        if (contentType.contains("multipart")) {
            Multipart multiPart = (Multipart) message.getContent();
            int numberOfParts = multiPart.getCount();
            for (int partCount = 0; partCount < numberOfParts; partCount++) {
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                messageContent = part.getContent().toString();
            }
        }
        else if (contentType.contains("text/plain")
                || contentType.contains("text/html")) {
            Object content = message.getContent();
            if (content != null) {
                messageContent = content.toString();
            }
        }
        return messageContent;
    }

}
