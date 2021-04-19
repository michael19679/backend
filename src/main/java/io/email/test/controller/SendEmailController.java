package io.email.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
@Configuration
@RequestMapping(value = "/")
public class SendEmailController {

    private static final Logger LOG = LogManager.getLogger(SendEmailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public SendEmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @GetMapping(path = "send-email")
    public ResponseEntity<String> sendEmail(@Value("${email-service.from}") String from,
                                            @Value("${email-service.to}") String to,
                                            @Value("${email-service.subject}") String subject,
                                            @Value("${email-service.body}") String body) {

        LOG.info("Attempting to send mail");

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, false);

            LOG.info("Sending email from {}, to {}. subject: {}", from, to, subject);
            LOG.info("Body: {}", body);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Email Sent");
    }
}
