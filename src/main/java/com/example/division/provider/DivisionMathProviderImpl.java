package com.example.division.provider;

import com.example.division.domain.DivisionResult;
import com.example.division.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

public class DivisionMathProviderImpl implements DivisionMathProvider {

    public DivisionResult provideMathDivision(long dividend, long divisor) {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        String dividendString = String.valueOf(dividend);
        long currentDividendPart = 0;

        for (int i = 0; i < dividendString.length(); i++) {
            currentDividendPart = currentDividendPart * 10 + dividendString.charAt(i) - '0';
            if (currentDividendPart >= divisor) {
                divisionSteps.add(new DivisionStep(currentDividendPart, currentDividendPart / divisor * divisor));
                currentDividendPart %= divisor;
            }
        }

        return new DivisionResult(dividend, divisor, divisionSteps);
    }
}
