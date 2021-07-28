package com.example.division;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.division.provider.DivisionMathProvider;
import com.example.division.provider.DivisionMathProviderImpl;
import com.example.division.provider.DivisionViewProvider;
import com.example.division.provider.DivisionViewProviderImpl;
import com.example.division.validator.DivisionValidator;
import org.junit.jupiter.api.Test;

class IntegerDivisionCalculatorITTest {

    private final IntegerDivisionCalculator calculator;

    IntegerDivisionCalculatorITTest() {
        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();
        DivisionValidator divisionValidator = new DivisionValidator();
        calculator = new IntegerDivisionCalculatorImpl(divisionMathProvider,
                divisionViewProvider,
                divisionValidator);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenDividendLength1AndDivisorLength1() {
        long dividend = 7;
        long divisor = 3;

        String expected = "_7|3\n" +
                          " 6|-\n" +
                          " -|2\n" +
                          " 1  ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenDividendLargerDivisorDividendLength1AndDivisorLength1() {
        long dividend = 5;
        long divisor = 8;

        String expected = "5|8\n" +
                          " |-\n" +
                          " |0";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenDividendLength2AndDivisorLength1ResultLength1() {
        long dividend = 49;
        long divisor = 9;

        String expected = "_49|9\n" +
                          " 45|-\n" +
                          " --|5\n" +
                          "  4  ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenDividendLength2AndDivisorLength1ResultLength2() {
        long dividend = 71;
        long divisor = 6;

        String expected = "_71|6 \n" +
                          " 6 |--\n" +
                          " - |11\n" +
                          "_11   \n" +
                          "  6   \n" +
                          " --   \n" +
                          "  5   ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenDividendLength2AndDivisorLength2() {
        long dividend = 82;
        long divisor = 14;

        String expected = "_82|14\n" +
                          " 70|--\n" +
                          " --|5 \n" +
                          " 12   ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenDividendLargerDivisorDividendLength2AndDivisorLength2() {
        long dividend = 53;
        long divisor = 64;

        String expected = "53|64\n" +
                          "  |--\n" +
                          "  |0 ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenLargeNumbers() {
        long dividend = 3289715628L;
        long divisor = 3673;

        String expected = "_3289715628|3673  \n" +
                          " 29384     |------\n" +
                          " -----     |895648\n" +
                          " _35131           \n" +
                          "  33057           \n" +
                          "  -----           \n" +
                          "  _20745          \n" +
                          "   18365          \n" +
                          "   -----          \n" +
                          "   _23806         \n" +
                          "    22038         \n" +
                          "    -----         \n" +
                          "    _17682        \n" +
                          "     14692        \n" +
                          "     -----        \n" +
                          "     _29908       \n" +
                          "      29384       \n" +
                          "      -----       \n" +
                          "        524       ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenManyZerosAtTheEndOfDividend() {
        long dividend = 16493000000L;
        long divisor = 39372;

        String expected = "_16493000000|39372 \n" +
                          " 157488     |------\n" +
                          " ------     |418901\n" +
                          "  _74420           \n" +
                          "   39372           \n" +
                          "   -----           \n" +
                          "  _350480          \n" +
                          "   314976          \n" +
                          "   ------          \n" +
                          "   _355040         \n" +
                          "    354348         \n" +
                          "    ------         \n" +
                          "      _69200       \n" +
                          "       39372       \n" +
                          "       -----       \n" +
                          "       29828       ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldReturnResultWhenRemainderIsLargerThanLastSubtractionResult() {
        long dividend = 1000005;
        long divisor = 10;

        String expected = "_1000005|10    \n" +
                          " 10     |------\n" +
                          " --     |100000\n" +
                          "  000005       ";
        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldThrowIllegalArgumentExceptionThenDivisorIsZero() {
        long dividend = 10;
        long divisor = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateDivision(dividend, divisor));
        assertEquals("Division by 0", exception.getMessage());
    }

    @Test
    void calculateDivisionShouldThrowIllegalArgumentExceptionThenDividendLessThanZero() {
        long dividend = -7;
        long divisor = 2;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateDivision(dividend, divisor));
        assertEquals("Dividend less than 0", exception.getMessage());
    }

    @Test
    void calculateDivisionShouldThrowIllegalArgumentExceptionThenDivisorLessThanZero() {
        long dividend = 16;
        long divisor = -3;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateDivision(dividend, divisor));
        assertEquals("Divisor less than 0", exception.getMessage());
    }

    @Test
    void calculateDivisionShouldThrowIllegalArgumentExceptionThenDividendAndDivisorLessThanZero() {
        long dividend = -15;
        long divisor = -6;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateDivision(dividend, divisor));
        assertEquals("Dividend less than 0", exception.getMessage());
    }

    @Test
    void calculateDivisionShouldNotThrowExceptionThenDivisorLargerOrEqualZeroAndDivisorLargerThanZero() {
        long dividend = 92;
        long divisor = 18;

        assertDoesNotThrow(() ->
                calculator.calculateDivision(dividend, divisor));
    }
}
