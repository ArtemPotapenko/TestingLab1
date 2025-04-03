package lib;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testing SecantService")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SecantServiceTest {

    private SecantService secantService =  new SecantService(0.1);

    @ParameterizedTest(name = "Testing sec({0})")
    @ValueSource(doubles = {0.0, -0.1, 0.1, 0.2, -0.2, 0.3, -0.3, 0.11, -0.11, 0.33, -0.33})
    void testValidValues(double x) {
        Assertions.assertEquals(1 / Math.cos(x), secantService.secant(x), 0.15);
    }

    @ParameterizedTest(name = "Testing domain sec({0})")
    @ValueSource(doubles = {Math.PI / 2, -Math.PI / 2, 3 * Math.PI / 2, -3 * Math.PI / 2})
    void testDomain(double x) {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, secantService.secant(x));
    }

    @ParameterizedTest(name = "Testing argument reduction sec({0})")
    @ValueSource(doubles = {3.0, 999, 22, 50, Math.PI, -Math.PI})
    void testArgumentReduction(double x) {
        Assertions.assertEquals(1 / Math.cos(x), secantService.secant(x), 0.1);
    }

    @ParameterizedTest(name = "Testing invalid values sec({0})")
    @ValueSource(doubles = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN})
    void testInvalidValues(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> secantService.secant(x));
    }


}