package com.example.division;

import com.example.division.domain.DivisionResult;
import com.example.division.provider.DivisionMathProvider;
import com.example.division.provider.DivisionViewProvider;
import com.example.division.validator.DivisionValidator;

public class IntegerDivisionCalculatorImpl implements IntegerDivisionCalculator {

    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;
    private final DivisionValidator divisionValidator;

    public IntegerDivisionCalculatorImpl(DivisionMathProvider divisionMathProvider,
                                         DivisionViewProvider divisionViewProvider,
                                         DivisionValidator divisionValidator) {
        this.divisionMathProvider = divisionMathProvider;
        this.divisionViewProvider = divisionViewProvider;
        this.divisionValidator = divisionValidator;
    }

    public String calculateDivision(long dividend, long divisor) {
        divisionValidator.validateDivision(dividend, divisor);

        DivisionResult divisionResult = divisionMathProvider.provideMathDivision(dividend, divisor);

        return divisionViewProvider.provideView(divisionResult);
    }
}
