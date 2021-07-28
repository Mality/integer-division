package com.example.division;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoInteractions;

import com.example.division.domain.DivisionResult;
import com.example.division.domain.DivisionStep;
import com.example.division.provider.DivisionMathProvider;
import com.example.division.provider.DivisionViewProvider;
import com.example.division.validator.DivisionValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class IntegerDivisionCalculatorImplTest {

    @Mock
    DivisionMathProvider mockedDivisionMathProvider;

    @Mock
    DivisionViewProvider mockedDivisionViewProvider;

    @Mock
    DivisionValidator mockedDivisionValidator;

    @InjectMocks
    IntegerDivisionCalculatorImpl calculator;

    @Test
    void calculateDivisionShouldReturnResultWhenSunnyDay() {
        long dividend = 95639;
        long divisor = 518;

        List<DivisionStep> subtractions = new ArrayList<>();
        subtractions.add(new DivisionStep(956, 518));
        subtractions.add(new DivisionStep(4383, 4144));
        subtractions.add(new DivisionStep(2399, 2072));

        String expected = "_95639|518\n" +
                          " 518  |---\n" +
                          " ---  |184\n" +
                          "_4383     \n" +
                          " 4144     \n" +
                          " ----     \n" +
                          " _2399    \n" +
                          "  2072    \n" +
                          "  ----    \n" +
                          "   327    ";

        DivisionResult expectedDivisionResult = new DivisionResult(dividend, divisor, subtractions);

        when(mockedDivisionMathProvider.provideMathDivision(dividend, divisor))
                .thenReturn(expectedDivisionResult);
        when(mockedDivisionViewProvider.provideView(expectedDivisionResult))
                .thenReturn(expected);

        String actual = calculator.calculateDivision(dividend, divisor);

        assertEquals(expected, actual);
    }

    @Test
    void calculateDivisionShouldThrowIllegalArgumentExceptionThenDivisionValidatorTrowsException() {
        long dividend = 10;
        long divisor = 5;

        Exception expectedException = new IllegalArgumentException("Division by 0");
        doThrow(expectedException)
                .when(mockedDivisionValidator)
                .validateDivision(dividend, divisor);

        Exception actualException = assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateDivision(dividend, divisor));
        assertEquals(expectedException, actualException);

        verifyNoInteractions(mockedDivisionMathProvider);
        verifyNoInteractions(mockedDivisionViewProvider);
    }
}
