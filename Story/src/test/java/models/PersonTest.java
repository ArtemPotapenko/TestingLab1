package models;

import exception.DuplicatePersonalityException;
import exception.InvalidSpaceException;
import exception.InvalidTemperatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Иван");
    }

    @Test
    @DisplayName("Проверка отрицательной скорости")
    void testGoByPlaceWithNegativeSpace() {
        InvalidSpaceException exception = assertThrows(InvalidSpaceException.class, () -> {
            person.goByPlace(new Place("парк"), -100);
        });
        assertEquals("Скорость не может быть отрицательной", exception.getMessage());
    }

    @Test
    @DisplayName("Проверка скорости NaN")
    void testGoByPlaceWithNaNSpace() {
        InvalidSpaceException exception = assertThrows(InvalidSpaceException.class, () -> {
            person.goByPlace(new Place("парк"), Double.NaN);
        });
        assertEquals("Скорость не может быть NaN", exception.getMessage());
    }

    @Test
    @DisplayName("Провека бесконечной скорости")
    void testGoByPlaceWithInfiniteSpace() {
        InvalidSpaceException exception = assertThrows(InvalidSpaceException.class, () -> {
            person.goByPlace(new Place("парк"), Double.POSITIVE_INFINITY);
        });
        assertEquals("Скорость не может быть NaN", exception.getMessage());
    }

    @Test
    @DisplayName("Тест -- человек идет")
    void testGoByPlaceWithValidSpeed() throws InvalidSpaceException {
        String result = person.goByPlace(new Place("парк"), 1000);
        assertEquals("Иван идет по парк", result);
    }

    @Test
    @DisplayName("Тест -- человек бежит")
    void testGoByPlaceWithHighSpeed() throws InvalidSpaceException {
        String result = person.goByPlace(new Place("парк"), 5000);
        assertEquals("Иван бежит по парк", result);
    }

    @Test
    void testSniff() {
        Smell smell = Smell.FLOWERS;
        String result = person.sniff(()->smell);
        assertEquals("Иван чувствует запах цветов", result);
    }

    @Test
    void testAddPersonality() throws DuplicatePersonalityException {
        Personality personality = Personality.KIND;
        person.addPersonality(personality);

        assertArrayEquals(new String[]{"Иван демонстрирует доброту"}, person.demonstratePersonality());
    }

    @Test
    void testAddDuplicatePersonalityThrowsException() {
        Personality personality = Personality.KIND;
        try {
            person.addFakePersonality(personality);
            assertThrows(DuplicatePersonalityException.class, () -> {
                person.addPersonality(personality);
            });
        } catch (DuplicatePersonalityException e) {
            assertEquals("Качество не может настоящим и фейковым", e.getMessage());
        }
    }

    @Test
    void testAddFakePersonality() throws DuplicatePersonalityException {
        Personality personality = Personality.SMART;
        person.addFakePersonality(personality);

        assertArrayEquals(new String[]{"Иван искуственно демонстрирует ум"}, person.demonstratePersonality());
    }

    @Test
    void testAddDuplicateFakePersonalityThrowsException() {
        Personality personality = Personality.SMART;
        try {
            person.addPersonality(personality);
            person.addFakePersonality(personality);
            assertThrows(DuplicatePersonalityException.class, () -> {
                person.addFakePersonality(personality);
            });
        } catch (DuplicatePersonalityException e) {
            assertEquals("Качество не может настоящим и фейковым", e.getMessage());
        }
    }

    @Test
    void testTouchWallCold() throws InvalidTemperatureException {
        Wall coldWall = new Wall("каменную", 5);
        String[] result = person.touchWall(coldWall);
        assertArrayEquals(new String[]{
                "Иван видит каменную стену",
                "Иван трогает стену",
                "Стена холодная"
        }, result);
    }

    @Test
    void testTouchWallHot() throws InvalidTemperatureException {
        Wall hotWall = new Wall("деревянную", 20);
        String[] result = person.touchWall(hotWall);
        assertArrayEquals(new String[]{
                "Иван видит деревянную стену",
                "Иван трогает стену",
                "Стена теплая"
        }, result);
    }

    @Test
    void testTouchWallWithColdTemperature() throws InvalidTemperatureException {
        Wall coldWall = new Wall("бетонную", -10);
        String[] result = person.touchWall(coldWall);
        assertArrayEquals(new String[]{
                "Иван видит бетонную стену",
                "Иван трогает стену",
                "Стена холодная"
        }, result);
    }

    @Test
    void testInvalidTemperatureThrowsException() {
        InvalidTemperatureException exception = assertThrows(InvalidTemperatureException.class, () -> {
            new Wall("стеклянная", Double.NaN);
        });
        assertEquals("Температура не может быть NaN", exception.getMessage());
    }

    @Test
    @DisplayName("Тестирование слишком горячей стены")
    void testInvalidTemperatureOutOfRangeThrowsException() {
        InvalidTemperatureException exception = assertThrows(InvalidTemperatureException.class, () -> {
            new Wall("металлическую", 150);
        });
        assertEquals("Тепмпература не может иметь температуру 150.0", exception.getMessage());
    }
}

