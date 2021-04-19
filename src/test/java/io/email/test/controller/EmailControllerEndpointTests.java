package io.email.test.controller;

import io.email.test.EmailApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = EmailApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmailControllerEndpointTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldSendMail() {
        //Given
        String url = "/send-email";

        //When
        ResponseEntity<String> result = testRestTemplate.getForEntity(url, String.class);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldDeleteMail() {
        //Given
        String url = "/delete-email";

        //When
        ResponseEntity<String> result = testRestTemplate.getForEntity(url, String.class);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReadOneMailWithDefinedSubject() {
        //Given
        String url = "/read-single-email";

        //When
        ResponseEntity<String> result = testRestTemplate.getForEntity(url, String.class);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReadAllMails() {
        //Given
        String url = "/read-emails";

        //When
        ResponseEntity<String> result = testRestTemplate.getForEntity(url, String.class);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}