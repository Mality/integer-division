package com.example.division.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class DivisionValidatorTest {

    DivisionValidator divisionValidator = new DivisionValidator();

    @Test
    void validateDivisionShouldThrowIllegalArgumentExceptionThenDivisorIsZero() {
        long dividend = 10;
        long divisor = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validateDivision(dividend, divisor));
        assertEquals("Division by 0", exception.getMessage());
    }

    @Test
    void validateDivisionShouldThrowIllegalArgumentExceptionThenDividendLessThanZero() {
        long dividend = -7;
        long divisor = 2;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validateDivision(dividend, divisor));
        assertEquals("Dividend less than 0", exception.getMessage());
    }

    @Test
    void validateDivisionShouldThrowIllegalArgumentExceptionThenDivisorLessThanZero() {
        long dividend = 16;
        long divisor = -3;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validateDivision(dividend, divisor));
        assertEquals("Divisor less than 0", exception.getMessage());
    }

    @Test
    void validateDivisionShouldThrowIllegalArgumentExceptionThenDividendAndDivisorLessThanZero() {
        long dividend = -15;
        long divisor = -6;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validateDivision(dividend, divisor));
        assertEquals("Dividend less than 0", exception.getMessage());
    }


    @Test
    void validateDivisionShouldNotThrowExceptionThenDivisorLargerOrEqualZeroAndDivisorLargerThanZero() {
        long dividend = 92;
        long divisor = 18;

        assertDoesNotThrow(() ->
                divisionValidator.validateDivision(dividend, divisor));
    }
}
