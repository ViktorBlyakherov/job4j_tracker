package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LambdaFunctionTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = new LambdaFunction().diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadratFunctionThenQuadratResults() {
        List<Double> result = new LambdaFunction().diapason(1, 3, x -> x * x + 1);
        List<Double> expected = Arrays.asList(2D, 5D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        List<Double> result = new LambdaFunction().diapason(1, 3, x -> Math.pow(3, x) + 1);
        List<Double> expected = Arrays.asList(4D, 10D);
        assertThat(result, is(expected));
    }
}