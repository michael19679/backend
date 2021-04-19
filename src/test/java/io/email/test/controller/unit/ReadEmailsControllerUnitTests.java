package io.email.test.controller.unit;

import io.email.test.controller.ReadEmailsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({MockitoExtension.class})
public class ReadEmailsControllerUnitTests {

    @InjectMocks
    private ReadEmailsController testee;

    @Test
    public void shouldReadAllEmails() {
        //Given
        String userName = "xxx@gmail.com";
        String password = "xxx";
        String pop3Host = "pop.gmail.com";

        //When
        ResponseEntity<String> result = testee.readEmails(userName, password, pop3Host);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldThrowExceptionDueToMissingPassword() {
        //Given
        String userName = "xxx@gmail.com";
        String password = "";
        String pop3Host = "pop.gmail.com";

        //When / Then
        assertThrows(RuntimeException.class, () -> testee.readEmails(userName, password, pop3Host));
    }
}
