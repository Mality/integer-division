package com.example.division.provider;

import com.example.division.domain.DivisionResult;

public interface DivisionMathProvider {

    DivisionResult provideMathDivision(long dividend, long divisor);
}
