package models;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlaceTest {

    @Test
    @DisplayName("Test add smell method")
    void testAddSmell() {
        Place place = new Place("Сад");
        Smell smell = Smell.DECAY;
        place.addSmell(smell, 0.7);
        assertEquals(1, place.getSmells().size());
    }

    @Test
    @DisplayName("Test dissipate smells method")
    void testDissipateSmells() {
        Place place = new Place("Лес");
        Smell smell = Smell.STINK;
        place.addSmell(smell, 1.0);
        place.dissipateSmells(5);
        assertTrue(place.getSmells().get(smell) < 1.0);
    }

    @Test
    @DisplayName("Test get strongest smell method")
    void testGetStrongestSmell() {
        Place place = new Place("Площадь");
        Smell smell1 = Smell.DECAY;
        Smell smell2 = Smell.FLOWERS;
        place.addSmell(smell1, 0.5);
        place.addSmell(smell2, 0.9);
        assertEquals(smell2, place.getStrongestSmell());
    }

    @Test
    @DisplayName("Test get strongest smell with no smells")
    void testGetStrongestSmellWithNoSmells() {
        Place place = new Place("Пустая комната");
        assertNull(place.getStrongestSmell());
    }
}