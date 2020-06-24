package com.beautifultestskata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubtractAverageTests {

    static Stream<ArrayOperations> provider() {
        return Stream.of(
                new ArrayOperationsV1()
                , new ArrayOperationsV2()
                , new ArrayOperationsV3()
                , new ArrayOperationsV4()
                , new ArrayOperationsV5()
                , new ArrayOperationsV6()
                , new ArrayOperationsV7()
                , new ArrayOperationsV8()
                , new ArrayOperationsWorking());
    }

    @ParameterizedTest
    @MethodSource("provider")
    void throws_exception_when_passed_null(ArrayOperations implementation) {
        assertThrows(IllegalArgumentException.class, () -> implementation.subtractAverage(null));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void throws_exception_when_passed_empty_array(ArrayOperations implementation) {
        assertThrows(IllegalArgumentException.class, () -> implementation.subtractAverage(new float[] {}));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void array_with_one_element_returns_array_containing_zero(ArrayOperations implementation) {
        assertAll(
                () -> assertArrayEquals(new float[] {0}, implementation.subtractAverage(new float[] {1})),
                () -> assertArrayEquals(new float[] {0}, implementation.subtractAverage(new float[] {-1})),
                () -> assertArrayEquals(new float[] {0}, implementation.subtractAverage(new float[] {-3}))
        );
    }

    @ParameterizedTest
    @MethodSource("provider")
    void handles_multiple_numbers_that_result_in_int_average(ArrayOperations implementation) {
        float[] output = implementation.subtractAverage(new float[] {2, 4, 6, 8});
        assertArrayEquals(new float[] {-3,-1, 1, 3}, output);
    }

    @ParameterizedTest
    @MethodSource("provider")
    void handles_multiple_numbers_that_result_in_float_average(ArrayOperations implementation) {
        float[] output = implementation.subtractAverage(new float[] {2, 2, 4, 2});
        assertArrayEquals(new float[] {-0.5f,-0.5f, 1.5f, -0.5f}, output);
    }

    @ParameterizedTest
    @MethodSource("provider")
    void throws_exception_when_array_contains_float_nan(ArrayOperations implementation) {
        assertThrows(IllegalArgumentException.class, () -> implementation.subtractAverage(new float[] {Float.NaN}));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void throws_exception_when_array_contains_float_infinity(ArrayOperations implementation) {
        assertThrows(IllegalArgumentException.class, () -> implementation.subtractAverage(new float[] {Float.POSITIVE_INFINITY}));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void throws_exception_when_array_contains_float_negative_infinity(ArrayOperations implementation) {
        assertThrows(IllegalArgumentException.class, () -> implementation.subtractAverage(new float[] {Float.NEGATIVE_INFINITY}));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void does_not_modify_the_input_array(ArrayOperations implementation) {
        float[] input = {1f, 3f};
        implementation.subtractAverage(input);
        assertArrayEquals(new float[] {1f, 3f}, input);
    }
}
