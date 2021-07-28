package com.example.division;

import com.example.division.provider.DivisionMathProvider;
import com.example.division.provider.DivisionMathProviderImpl;
import com.example.division.provider.DivisionViewProvider;
import com.example.division.provider.DivisionViewProviderImpl;
import com.example.division.validator.DivisionValidator;

public class IntegerDivisorConsoleApplication {

    public static void main(String[] args) {
        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();
        DivisionValidator divisionValidator = new DivisionValidator();
        IntegerDivisionCalculator calculator = new IntegerDivisionCalculatorImpl(divisionMathProvider,
                divisionViewProvider,
                divisionValidator);
        long dividend = 95639;
        long divisor = 518;

        System.out.println(calculator.calculateDivision(dividend, divisor));
    }
}
