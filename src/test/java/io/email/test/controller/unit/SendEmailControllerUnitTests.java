package io.email.test.controller.unit;

import io.email.test.controller.SendEmailController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({MockitoExtension.class})
public class SendEmailControllerUnitTests {

    @InjectMocks
    private SendEmailController testee;

    @Test
    public void shouldSendEmail() {
        //Given
        String from = "xxx@gmail.com";
        String to = "xxx@gmail.com";
        String subject = "xxx";
        String body = "pop.gmail.com";

        //When
        ResponseEntity<String> result = testee.sendEmail(from, to, subject, body);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldThrowExceptionDueToMissingFromAddress() {
        //Given
        String from = "";
        String to = "xxx@gmail.com";
        String subject = "xxx";
        String body = "pop.gmail.com";

        //When / Then
        assertThrows(RuntimeException.class, () -> testee.sendEmail(from, to, subject, body));
    }
}
