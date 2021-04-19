package io.email.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import java.util.Properties;

import static io.email.test.util.PropertiesUtil.getStore;

@RestController
@Configuration
@RequestMapping(value = "/")
public class DeleteEmailController {

    private static final Logger LOG = LogManager.getLogger(DeleteEmailController.class);

    @GetMapping(path = "delete-email")
    public ResponseEntity<String> deleteMail(@Value("${subjectToDelete}")
                                                     String subjectToDelete, @Value("${spring.mail.pop3.username}")
                                                     String userName, @Value("${spring.mail.pop3.password}")
                                                     String password, @Value("${spring.mail.pop3.host}")
                                                     String pop3Host) {

        try {
            Store store = getStore(userName, password, pop3Host);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                LOG.info("---------------------------------");
                LOG.info("Email Number " + (i + 1));
                LOG.info("Subject: " + message.getSubject());
                LOG.info("From: " + message.getFrom()[0]);

                String subject = message.getSubject();
                if (subject.equals(subjectToDelete)) {
                    message.setFlag(Flags.Flag.DELETED, true);
                    LOG.info("Marked DELETE for message: " + subject);
                } else {
                    LOG.info("Message subject not found");
                }
            }
            emailFolder.close(true);
            store.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Email Deleted");
    }
}
