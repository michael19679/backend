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

import static io.email.test.util.EmailContentUtil.getContentOfEmail;
import static io.email.test.util.PropertiesUtil.getStore;

@RestController
@Configuration
@RequestMapping(value = "/")
public class ReadSingleEmailController {

    private static final Logger LOG = LogManager.getLogger(ReadEmailsController.class);

    @GetMapping(path = "read-single-email")
    public ResponseEntity<String> readSingleEmail(@Value("${subjectToFind}")
                                                          String subjectToFind, @Value("${spring.mail.pop3.username}")
                                                          String userName, @Value("${spring.mail.pop3.password}")
                                                          String password, @Value("${spring.mail.pop3.host}")
                                                          String pop3Host) {

        try {
            Store store = getStore(userName, password, pop3Host);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            LOG.info("messages.length---" + messages.length);

            for (Message message : messages) {
                LOG.info("Searching for your chosen subject...");
                if (message.getSubject().equals(subjectToFind)) {
                    LOG.info("Message found " + subjectToFind);
                    LOG.info("-------------------------------");
                    LOG.info("Date : " + message.getSentDate());
                    LOG.info("Subject: " + message.getSubject());
                    LOG.info("From: " + message.getFrom()[0]);
                    LOG.info("Text: " + getContentOfEmail(message));

                    LOG.info("--------------------------------");
                } else {
                    LOG.info("Could not find any mails with subject: " + subjectToFind);
                }
            }
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Email Read");
    }
}
