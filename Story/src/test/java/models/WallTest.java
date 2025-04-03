package models;

import static org.junit.jupiter.api.Assertions.*;

import exception.InvalidTemperatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WallTest {

    private Wall wall;

    @BeforeEach
    void setUp() throws InvalidTemperatureException {
        wall = new Wall("белая", 20, Material.METAL);
    }

    @Test
    @DisplayName("Test wall temperature")
    void testWallTemperature() {
        assertEquals(20, wall.getTemperature());
    }

    @Test
    @DisplayName("Test invalid temperature throws exception")
    void testInvalidTemperatureThrowsException() {
        assertThrows(InvalidTemperatureException.class, () -> new Wall("белая", 200, Material.METAL));
        assertThrows(InvalidTemperatureException.class, () -> new Wall("белая", Double.NaN, Material.METAL));
    }

    @Test
    @DisplayName("Test heat up method")
    void testHeatUp() {
        wall.heatUp(10);
        assertTrue(wall.getTemperature() > 20);
    }

    @Test
    @DisplayName("Test max temperature on heat up")
    void testHeatUpWithMaxTemperature() {
        wall.heatUp(1000);
        assertEquals(100, wall.getTemperature());
    }

    @Test
    @DisplayName("Test cool down method")
    void testCoolDown() {
        wall.coolDown(10);
        assertTrue(wall.getTemperature() < 20);
    }

    @Test
    @DisplayName("Test min temperature on cool down")
    void testCoolDownWithMinTemperature() {
        wall.coolDown(1000);
        assertEquals(-100, wall.getTemperature());
    }

    @Test
    @DisplayName("Test illuminate method")
    void testIlluminate() {
        wall.illuminate(10, 2);
        assertTrue(wall.getLightLevel() > 0);
    }

    @Test
    @DisplayName("Test max light level on illuminate")
    void testIlluminateWithMaxLightLevel() {
        wall.illuminate(1000, 1000);
        assertEquals(100, wall.getLightLevel());
    }

    @Test
    @DisplayName("Test is visible method")
    void testIsVisible() {
        wall.illuminate(11, 2);
        assertTrue(!wall.isVisible());
        wall.illuminate(40, 2);
        assertTrue(wall.isVisible());
    }

    @Test
    @DisplayName("Test is not visible method")
    void testIsNotVisible() {
        wall.illuminate(1, 1);
        assertFalse(wall.isVisible());
    }

    @Test
    @DisplayName("Test is not valid temp")
    void testTemperatureIsNotValid(){
        Assertions.assertThrows( InvalidTemperatureException.class,()-> new Wall("", Double.NaN, Material.GLASS));
        Assertions.assertThrows( InvalidTemperatureException.class,()-> new Wall("", -150, Material.GLASS));
        Assertions.assertThrows( InvalidTemperatureException.class,()-> new Wall("", 150, Material.GLASS));
        Assertions.assertThrows( InvalidTemperatureException.class,()-> new Wall("", Double.POSITIVE_INFINITY, Material.GLASS));
        Assertions.assertThrows( InvalidTemperatureException.class,()-> new Wall("", Double.POSITIVE_INFINITY, Material.GLASS));
    }
}