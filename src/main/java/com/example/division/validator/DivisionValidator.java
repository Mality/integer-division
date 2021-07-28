package com.example.division.validator;

public class DivisionValidator {

    public void validateDivision(long dividend, long divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Division by 0");
        }
        
        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend less than 0");
        }
        if (divisor < 0) {
            throw new IllegalArgumentException("Divisor less than 0");
        }
    }
}
