package com.example.division.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.division.domain.DivisionResult;
import com.example.division.domain.DivisionStep;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class DivisionViewProviderImplTest {

    private final DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

    @Test
    void provideViewShouldReturnResultWhenDividendLength1AndDivisorLength1() {
        long dividend = 7;
        long divisor = 3;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(7, 6));

        String expected =
                "_7|3\n" +
                " 6|-\n" +
                " -|2\n" +
                " 1  ";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenDividendLargerDivisorDividendLength1AndDivisorLength1() {
        long dividend = 5;
        long divisor = 8;

        List<DivisionStep> subtractions = new ArrayList<>();

        String expected =
                "5|8\n" +
                " |-\n" +
                " |0";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenDividendLength2AndDivisorLength1ResultLength1() {
        long dividend = 49;
        long divisor = 9;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(49, 45));

        String expected =
                "_49|9\n" +
                " 45|-\n" +
                " --|5\n" +
                "  4  ";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenDividendLength2AndDivisorLength1ResultLength2() {
        long dividend = 71;
        long divisor = 6;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(7, 6));
        subtractions.add(new DivisionStep(11, 6));

        String expected =
                "_71|6 \n" +
                " 6 |--\n" +
                " - |11\n" +
                "_11   \n" +
                "  6   \n" +
                " --   \n" +
                "  5   ";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenDividendLength2AndDivisorLength2() {
        long dividend = 82;
        long divisor = 14;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(82, 70));

        String expected =
                "_82|14\n" +
                " 70|--\n" +
                " --|5 \n" +
                " 12   ";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenDividendLargerDivisorDividendLength2AndDivisorLength2() {
        long dividend = 53;
        long divisor = 64;

        List<DivisionStep> subtractions = new ArrayList<>();

        String expected =
                "53|64\n" +
                "  |--\n" +
                "  |0 ";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenLargeNumbers() {
        long dividend = 3289715628L;
        long divisor = 3673;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(32897, 29384));
        subtractions.add(new DivisionStep(35131, 33057));
        subtractions.add(new DivisionStep(20745, 18365));
        subtractions.add(new DivisionStep(23806, 22038));
        subtractions.add(new DivisionStep(17682, 14692));
        subtractions.add(new DivisionStep(29908, 29384));

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

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewShouldReturnResultWhenManyZerosAtTheEndOfDividend() {
        long dividend = 16493000000L;
        long divisor = 39372;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(164930, 157488));
        subtractions.add(new DivisionStep(74420, 39372));
        subtractions.add(new DivisionStep(350480, 314976));
        subtractions.add(new DivisionStep(355040, 354348));
        subtractions.add(new DivisionStep(69200, 39372));

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

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }

    @Test
    void provideViewDivisionShouldReturnResultWhenRemainderIsLargerThanLastSubtractionResult() {
        long dividend = 1000005;
        long divisor = 10;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(10, 10));

        String expected = "_1000005|10    \n" +
                " 10     |------\n" +
                " --     |100000\n" +
                "  000005       ";

        DivisionResult divisionResult = new DivisionResult(dividend, divisor, subtractions);
        String actual = divisionViewProvider.provideView(divisionResult);

        assertEquals(expected, actual);
    }
}
