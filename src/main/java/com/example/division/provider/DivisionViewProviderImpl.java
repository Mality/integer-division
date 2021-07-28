package com.example.division.provider;

import com.example.division.domain.DivisionResult;
import com.example.division.domain.DivisionStep;

import java.util.Arrays;
import java.util.List;

public class DivisionViewProviderImpl implements DivisionViewProvider {

    private static final int ZERO_QUOTIENT_GRID_HEIGHT = 3;

    private static final char HORIZONTAL_SEPARATION_SYMBOL = '-';
    private static final char VERTICAL_SEPARATION_SYMBOL = '|';
    private static final char MINUS_SYMBOL = '_';

    public String provideView(DivisionResult divisionResult) {
        String dividend = String.valueOf(divisionResult.getDividend());
        String divisor = String.valueOf(divisionResult.getDivisor());
        String quotient = String.valueOf(divisionResult.getQuotient());
        String remainder = String.valueOf(divisionResult.getRemainder());
        List<DivisionStep> subtractions = divisionResult.getSubtractions();

        if (subtractions.isEmpty()) {
            return provideMathDivisionWithZeroQuotient(dividend, divisor);
        }

        return provideMathDivisionWithNonZeroQuotient(dividend,
                divisor,
                quotient,
                remainder,
                subtractions);
    }

    private static String provideMathDivisionWithNonZeroQuotient(String dividend,
                                                                 String divisor,
                                                                 String quotient,
                                                                 String remainder,
                                                                 List<DivisionStep> subtractions) {
        int horizontalBarLength = Math.max(divisor.length(), quotient.length());
        int gridHeight = subtractions.size() * 3 + 1;
        int gridWidth = 1 + dividend.length() + 1 + horizontalBarLength;
        char[][] charsGrid = new char[gridHeight][gridWidth];

        fillCharsGridWithSpaces(charsGrid);

        charsGrid[0][0] = MINUS_SYMBOL;
        charsGrid[0][dividend.length() + 1] = VERTICAL_SEPARATION_SYMBOL;
        charsGrid[1][dividend.length() + 1] = VERTICAL_SEPARATION_SYMBOL;
        charsGrid[2][dividend.length() + 1] = VERTICAL_SEPARATION_SYMBOL;

        addHorizontalSeparationSymbolToGrid(charsGrid,
                1,
                dividend.length() + 2,
                dividend.length() + horizontalBarLength + 1);

        addHorizontalSeparationSymbolToGrid(charsGrid,
                1,
                dividend.length() + 2,
                dividend.length() + horizontalBarLength + 1
        );

        addHorizontalStringToGrid(charsGrid, 0, 1, dividend);

        addHorizontalStringToGrid(charsGrid, 0, dividend.length() + 2, divisor);

        addHorizontalStringToGrid(charsGrid, 2, dividend.length() + 2, quotient);

        addAllSubtractionsAndRemainderToGrid(charsGrid, subtractions, dividend.length(), remainder);

        return charsGridToString(charsGrid);
    }

    private static String provideMathDivisionWithZeroQuotient(String dividend, String divisor) {
        int width = dividend.length() + 1 + divisor.length();
        char[][] charsGrid = new char[ZERO_QUOTIENT_GRID_HEIGHT][width];

        fillCharsGridWithSpaces(charsGrid);

        charsGrid[0][dividend.length()] = VERTICAL_SEPARATION_SYMBOL;
        charsGrid[1][dividend.length()] = VERTICAL_SEPARATION_SYMBOL;
        charsGrid[2][dividend.length()] = VERTICAL_SEPARATION_SYMBOL;

        addHorizontalSeparationSymbolToGrid(charsGrid,
                1,
                dividend.length() + 1,
                dividend.length() + divisor.length()
        );

        addHorizontalStringToGrid(charsGrid, 0, 0, dividend);

        addHorizontalStringToGrid(charsGrid, 0, dividend.length() + 1, divisor);

        addHorizontalStringToGrid(charsGrid, 0, dividend.length() + 1, divisor);

        charsGrid[2][dividend.length() + 1] = '0';

        return charsGridToString(charsGrid);
    }

    private static void addAllSubtractionsAndRemainderToGrid(char[][] charsGrid,
                                                             List<DivisionStep> subtractions,
                                                             int dividendLength,
                                                             String remainder) {
        int leftOffset = 0;

        for (int i = 0; i < subtractions.size(); i++) {
            DivisionStep step = subtractions.get(i);
            String firstNumber = String.valueOf(step.getFirstNumber());
            String secondNumber = String.valueOf(step.getSecondNumber());
            String resultNumber = String.valueOf(step.getResultNumber());

            int topOffset = i * 3;
            int secondNumberLeftOffset = firstNumber.length() - secondNumber.length();
            int resultNumberLeftOffset = firstNumber.length() - resultNumber.length();

            addHorizontalStringToGrid(charsGrid, topOffset, leftOffset + 1, firstNumber);

            charsGrid[topOffset][leftOffset] = '_';

            addHorizontalStringToGrid(charsGrid,
                    topOffset + 1,
                    leftOffset + 1 + secondNumberLeftOffset,
                    secondNumber);

            addHorizontalSeparationSymbolToGrid(charsGrid,
                    topOffset + 2,
                    leftOffset + 1,
                    leftOffset + firstNumber.length()
            );

            addHorizontalStringToGrid(charsGrid,
                    topOffset + 3,
                    leftOffset + 1 + resultNumberLeftOffset,
                    resultNumber);

            leftOffset += resultNumberLeftOffset;
        }

        addRemainderToGrid(charsGrid,
                subtractions.size() * 3,
                leftOffset + 1,
                dividendLength,
                remainder);
    }

    private static void addHorizontalStringToGrid(char[][] charsGrid,
                                                  int topOffset,
                                                  int leftOffset,
                                                  String string) {
        for (int i = leftOffset; i < leftOffset + string.length(); i++) {
            charsGrid[topOffset][i] = string.charAt(i - leftOffset);
        }
    }

    private static void addHorizontalSeparationSymbolToGrid(char[][] charsGrid,
                                                  int topOffset,
                                                  int leftBound,
                                                  int rightBound) {
        for (int i = leftBound; i <= rightBound; i++) {
            charsGrid[topOffset][i] = HORIZONTAL_SEPARATION_SYMBOL;
        }
    }

    private static void addRemainderToGrid(char[][] charsGrid,
                                           int topOffset,
                                           int leftBound,
                                           int rightBound,
                                           String remainder) {
        for (int i = leftBound; i <= rightBound; i++) {
            int numberReversedPosition = rightBound - i;

            if (remainder.length() > numberReversedPosition) {
                int numberPosition = remainder.length() - numberReversedPosition - 1;
                charsGrid[topOffset][i] = remainder.charAt(numberPosition);
            } else {
                charsGrid[topOffset][i] = '0';
            }
        }
    }

    private static String charsGridToString(char[][] charsGrid) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < charsGrid.length; i++) {
            for (int j = 0; j < charsGrid[i].length; j++) {
                string.append(charsGrid[i][j]);
            }
            if (i + 1 < charsGrid.length) {
                string.append('\n');
            }
        }
        return string.toString();
    }

    private static void fillCharsGridWithSpaces(char[][] charsGrid) {
        for (char[] chars : charsGrid) {
            Arrays.fill(chars, ' ');
        }
    }
}
