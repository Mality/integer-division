package com.example.division.domain;

import java.util.Objects;

public class DivisionStep {

    private final long firstNumber;
    private final long secondNumber;
    private final long resultNumber;

    public DivisionStep(long firstNumber, long secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        resultNumber = firstNumber - secondNumber;
    }

    public long getFirstNumber() {
        return firstNumber;
    }

    public long getSecondNumber() {
        return secondNumber;
    }

    public long getResultNumber() {
        return resultNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionStep that = (DivisionStep) o;
        return firstNumber == that.firstNumber &&
                secondNumber == that.secondNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumber, secondNumber);
    }

    @Override
    public String toString() {
        return "DivisionStep{" +
                "firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                ", resultNumber=" + resultNumber +
                '}';
    }
}
