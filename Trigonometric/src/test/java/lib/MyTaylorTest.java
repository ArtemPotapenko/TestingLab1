package lib;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Тестирование функции sec(x)")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MyTaylorTest {
    @ParameterizedTest(name = "Проверка значения {0}")
    @ValueSource(doubles = {0.0,
            -0.1, 0.1,
            0.2, -0.2,
            0.3, -0.3,
            0.11, -0.11,
            0.33, -0.33,
            0.45, -0.45,
            3.0, 5.0, 10.0,
            999, 100, 22,
            Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
            Double.NaN, Math.PI,
            -Math.PI
    })
    public void testEquals(double x) {
        Assertions.assertEquals(1 / Math.cos(x), MyTaylor.sec(x), 0.1);
    }
}
