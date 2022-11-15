package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputValidatorTest {

    @Test
    void inputValidator() {
        InputValidator mockInputValidator = mock(InputValidator.class);

        String message = "Type in an integer";
        int actualInput = 10;

        int expectedInput = 10;
        when(mockInputValidator.inputValidator(actualInput,message)).thenReturn(expectedInput);

        assertEquals(expectedInput,actualInput);
    }

    @Test
    void stringValidator() {
        InputValidator mockInputValidator = mock(InputValidator.class);

        String actualInput = "Hello";
        String expectedInput = "Hello";

        when(mockInputValidator.stringValidator(actualInput)).thenReturn(expectedInput);

        assertEquals(expectedInput,actualInput);

    }
}