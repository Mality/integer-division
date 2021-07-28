package com.example.division.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.division.domain.DivisionResult;
import com.example.division.domain.DivisionStep;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class DivisionMathProviderImplTest {

    private final DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();

    @Test
    void provideMathDivisionShouldReturnResultWhenDividendLength1AndDivisorLength1() {
        long dividend = 7;
        long divisor = 3;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(7, 6));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenDividendLargerDivisorDividendLength1AndDivisorLength1() {
        long dividend = 5;
        long divisor = 8;

        List<DivisionStep> subtractions = new ArrayList<>();

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenDividendLength2AndDivisorLength1ResultLength1() {
        long dividend = 49;
        long divisor = 9;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(49, 45));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenDividendLength2AndDivisorLength1ResultLength2() {
        long dividend = 71;
        long divisor = 6;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(7, 6));
        subtractions.add(new DivisionStep(11, 6));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenDividendLength2AndDivisorLength2() {
        long dividend = 82;
        long divisor = 14;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(82, 70));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenDividendLargerDivisorDividendLength2AndDivisorLength2() {
        long dividend = 53;
        long divisor = 64;

        List<DivisionStep> subtractions = new ArrayList<>();

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenLargeNumbers() {
        long dividend = 3289715628L;
        long divisor = 3673;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(32897, 29384));
        subtractions.add(new DivisionStep(35131, 33057));
        subtractions.add(new DivisionStep(20745, 18365));
        subtractions.add(new DivisionStep(23806, 22038));
        subtractions.add(new DivisionStep(17682, 14692));
        subtractions.add(new DivisionStep(29908, 29384));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenManyZerosAtTheEndOfDividend() {
        long dividend = 16493000000L;
        long divisor = 39372;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(164930, 157488));
        subtractions.add(new DivisionStep(74420, 39372));
        subtractions.add(new DivisionStep(350480, 314976));
        subtractions.add(new DivisionStep(355040, 354348));
        subtractions.add(new DivisionStep(69200, 39372));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivisionShouldReturnResultWhenRemainderIsLargerThanLastSubtractionResult() {
        long dividend = 1000005;
        long divisor = 10;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(10, 10));

        DivisionResult expected = new DivisionResult(dividend, divisor, subtractions);
        DivisionResult actual = divisionMathProvider.provideMathDivision(dividend, divisor);

        assertEquals(expected, actual);
    }
}
