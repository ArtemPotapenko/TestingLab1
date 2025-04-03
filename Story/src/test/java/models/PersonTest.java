package models;

import static org.junit.jupiter.api.Assertions.*;

import exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonTest {

    private Person person;
    private Place place;

    @BeforeEach
    void setUp() {
        person = new Person("Иван");
        place = new Place("Парк");
    }

    @Test
    @DisplayName("Test goByPlace with speed less than 3000")
    void testGoByPlaceWithSpeedLessThan3000() throws InvalidSpaceException {
        person.adjustSpeed(10);
        String result = person.goByPlace(place);
        assertEquals("Иван идет по Парк", result);
    }

    @Test
    @DisplayName("Test goByPlace with speed greater than 3000")
    void testGoByPlaceWithSpeedGreaterThan3000() throws InvalidSpaceException {
        person.adjustSpeed(3500);
        String result = person.goByPlace(place);
        assertEquals("Иван бежит по Парк", result);
    }

    @Test
    @DisplayName("Test goByPlace with invalid speed")
    void testGoByPlaceWithInvalidSpeed() {
        person.adjustSpeed(-10);
        assertThrows(InvalidSpaceException.class, () -> person.goByPlace(place));
        person.adjustSpeed(Double.NaN);
        assertThrows(InvalidSpaceException.class, () -> person.goByPlace(place));
        person.adjustSpeed(Double.POSITIVE_INFINITY);
        assertThrows(InvalidSpaceException.class, () -> person.goByPlace(place));
        person.adjustSpeed(Double.NEGATIVE_INFINITY);
        assertThrows(InvalidSpaceException.class, () -> person.goByPlace(place));
    }

    @Test
    @DisplayName("Test sniff method")
    void testSniff() {
        Smell smell = Smell.FLOWERS;
        String result = person.sniff(() -> smell);
        assertEquals("Иван чувствует аромат цветов", result);
    }

    @Test
    @DisplayName("Test add personality method")
    void testAddPersonality() throws DuplicatePersonalityException {
        Personality personality = Personality.KIND;
        person.addPersonality(personality);
        String[] personalities = person.demonstratePersonality();
        assertEquals(1, personalities.length);
        assertEquals("Иван демонстрирует доброту", personalities[0]);
    }

    @Test
    @DisplayName("Test add fake personality method")
    void testAddFakePersonality() throws DuplicatePersonalityException {
        Personality personality = Personality.NERVOUS;
        person.addFakePersonality(personality);
        String[] personalities = person.demonstratePersonality();
        assertEquals(1, personalities.length);
        assertEquals("Иван искусственно нервничает", personalities[0]);
    }

    @Test
    @DisplayName("Test add duplicate personality throws exception")
    void testAddDuplicatePersonalityThrowsException() throws DuplicatePersonalityException {
        Personality personality = Personality.KIND;
        person.addPersonality(personality);
        assertThrows(DuplicatePersonalityException.class, () -> person.addFakePersonality(personality));

        person.addFakePersonality(Personality.NERVOUS);
        assertThrows(DuplicatePersonalityException.class, () -> person.addPersonality(Personality.NERVOUS));
    }

    @Test
    @DisplayName("Test touch wall method")
    void testTouchWall() throws InvalidTemperatureException {
        Wall wall = new Wall("белая", 10, Material.GLASS);
        String[] result = person.touchWall(wall);
        assertEquals(3, result.length);
        assertEquals("Иван видит белая стену", result[0]);
        assertEquals("Иван трогает стену", result[1]);
        assertEquals("Стена теплая", result[2]);

        wall.setTemperature(9);
        result = person.touchWall(wall);
        assertEquals("Стена холодная", result[2]);
    }

    @Test
    @DisplayName("Test shine flashlight method")
    void testShineFlashlight() throws InvalidTemperatureException {
        Wall wall = new Wall("черная", 15, Material.GLASS);
        String result = person.shineFlashlight(wall, 5, 2);
        assertTrue(result.contains("Теперь ее видно"));
    }

    @Test
    @DisplayName("Test sniff around method")
    void testSniffAround() {
        place.addSmell(Smell.FLOWERS, 10);
        String result = person.sniffAround(place);
        assertEquals("Иван чувствует аромат цветов", result);
        assertEquals(person.sniffAround(new Place("Пустая")), "Иван не чувствует запахов.");
    }
}
