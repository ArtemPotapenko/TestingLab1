package models;

import exception.InvalidTemperatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование работы со стеной")
public class WallTest {
    @Test
    @DisplayName("Тестирование конструктора")
    public void test() {
        Assertions.assertThrows(InvalidTemperatureException.class, () -> new Wall("", 200));
        Assertions.assertThrows(InvalidTemperatureException.class, () -> new Wall("", -200));
        Assertions.assertThrows(InvalidTemperatureException.class, () -> new Wall("", Double.POSITIVE_INFINITY));
        Assertions.assertThrows(InvalidTemperatureException.class, () -> new Wall("", Double.NaN));
    }
}
