package io.email.test.controller.unit;

import io.email.test.controller.DeleteEmailController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({MockitoExtension.class})
public class DeleteEmailControllerUnitTests {

    @InjectMocks
    private DeleteEmailController testee;

    @Test
    public void shouldFindSubjectAndDeleteEmailSuccessfully() {
        //Given
        String subjectToDelete = "xxx";
        String userName = "xxx@gmail.com";
        String password = "xxx";
        String pop3Host = "pop.gmail.com";

        //When
        ResponseEntity<String> result = testee.deleteMail(subjectToDelete, userName, password, pop3Host);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldThrowExceptionDueToInvalidHost() {
        //Given
        String subjectToDelete = "xxx";
        String userName = "xxx@gmail.com";
        String password = "xxx";
        String pop3Host = "pop.gmail.com";

        //When / Then
        assertThrows(RuntimeException.class, () -> testee.deleteMail(subjectToDelete, userName, password, pop3Host));
    }
}
