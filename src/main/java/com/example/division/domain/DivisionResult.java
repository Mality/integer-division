package com.example.division.domain;

import java.util.List;
import java.util.Objects;

public class DivisionResult {

    private final long dividend;
    private final long divisor;
    private final long quotient;
    private final long remainder;
    private final List<DivisionStep> subtractions;

    public DivisionResult(long dividend, long divisor, List<DivisionStep> subtractions) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.quotient = dividend / divisor;
        this.remainder = dividend % divisor;
        this.subtractions = subtractions;
    }

    public long getDividend() {
        return dividend;
    }

    public long getDivisor() {
        return divisor;
    }

    public long getQuotient() {
        return quotient;
    }

    public long getRemainder() {
        return remainder;
    }

    public List<DivisionStep> getSubtractions() {
        return subtractions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionResult that = (DivisionResult) o;
        return dividend == that.dividend &&
                divisor == that.divisor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividend, divisor);
    }

    @Override
    public String toString() {
        return "DivisionResult{" +
                "dividend=" + dividend +
                ", divisor=" + divisor +
                ", quotient=" + quotient +
                ", remainder=" + remainder +
                ", subtractions=" + subtractions +
                '}';
    }
}
