package service;

import models.Lighting;
import models.Person;
import models.Position;
import models.WallLightingPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeopleServiceTest {
    private PeopleService peopleService;
    private Person person;

    @BeforeEach
    void setUp() {
        peopleService = new PeopleService();
        person = new Person();
        Position position = new Position(0, 0);
        person.setPosition(position);
        person.setSpeed(1.0);
        person.setHeight(1.8);
    }

    @Test
    void testRun() {
        peopleService.run(person, 90, 1);
        assertEquals(0, person.getPosition().getX(), 0.01);
        assertEquals(1.0, person.getPosition().getY(), 0.01);
        assertNotNull(person.getPosition());
        assertTrue(person.getSpeed() > 0);
    }

    @Test
    void testRunWithZeroTime() {
        peopleService.run(person, 45, 0);
        assertEquals(0, person.getPosition().getX(), 0.01);
        assertEquals(0, person.getPosition().getY(), 0.01);
        assertNotNull(person.getPosition());
    }

    @Test
    void testRunWithNegativeTime() {
        peopleService.run(person, 90, -1);
        assertEquals(0, person.getPosition().getX(), 0.01);
        assertEquals(-1.0, person.getPosition().getY(), 0.01);
        assertTrue(person.getSpeed() > 0);
    }

    @Test
    void testLight() {
        Lighting lighting = new Lighting();
        lighting.setRadius(5);
        lighting.setRadiusFactor(0.5);
        lighting.setPower(100);
        lighting.setDistanceFactor(2);

        List<WallLightingPosition> wallLightingPositions = new ArrayList<>();
        WallLightingPosition pos1 = new WallLightingPosition(0, 1, 0);
        WallLightingPosition pos2 = new WallLightingPosition(2, 2, 0);
        pos1.setLightLevel(0);
        pos2.setLightLevel(0);
        wallLightingPositions.add(pos1);
        wallLightingPositions.add(pos2);

        peopleService.light(person, wallLightingPositions, lighting);

        assertTrue(pos1.getLightLevel() > 0);
        assertTrue(pos2.getLightLevel() > 0);
        assertTrue(pos1.getLightLevel() > pos2.getLightLevel());
        assertNotEquals(pos1.getLightLevel(), pos2.getLightLevel());
    }

    @Test
    void testLightWithMultipleWallLightingPositions() {
        Lighting lighting = new Lighting();
        lighting.setRadius(5);
        lighting.setRadiusFactor(0.5);
        lighting.setPower(100);
        lighting.setDistanceFactor(2);

        List<WallLightingPosition> wallLightingPositions = new ArrayList<>();
        WallLightingPosition pos1 = new WallLightingPosition(1, 1, 0);
        WallLightingPosition pos2 = new WallLightingPosition(3, 3, 0);
        WallLightingPosition pos3 = new WallLightingPosition(5, 5, 0);
        pos1.setLightLevel(0);
        pos2.setLightLevel(0);
        pos3.setLightLevel(0);
        wallLightingPositions.add(pos1);
        wallLightingPositions.add(pos2);
        wallLightingPositions.add(pos3);

        peopleService.light(person, wallLightingPositions, lighting);

        assertTrue(pos1.getLightLevel() > pos2.getLightLevel());
        assertTrue(pos2.getLightLevel() > pos3.getLightLevel());
        assertTrue(pos1.getLightLevel() > 0);
        assertTrue(pos3.getLightLevel() >= 0);
    }

    @Test
    void testLightWithDifferentLightingPower() {
        Lighting lighting1 = new Lighting();
        lighting1.setRadius(5);
        lighting1.setRadiusFactor(0.5);
        lighting1.setPower(50);
        lighting1.setDistanceFactor(2);

        Lighting lighting2 = new Lighting();
        lighting2.setRadius(5);
        lighting2.setRadiusFactor(0.5);
        lighting2.setPower(200);
        lighting2.setDistanceFactor(2);

        List<WallLightingPosition> wallLightingPositions = new ArrayList<>();
        WallLightingPosition pos1 = new WallLightingPosition(1, 1, 0);
        pos1.setLightLevel(0);
        wallLightingPositions.add(pos1);

        peopleService.light(person, wallLightingPositions, lighting1);
        double lightLevel1 = pos1.getLightLevel();
        assertTrue(lightLevel1 > 0);

        pos1.setLightLevel(0);
        peopleService.light(person, wallLightingPositions, lighting2);
        double lightLevel2 = pos1.getLightLevel();
        assertTrue(lightLevel2 > lightLevel1);
        assertNotEquals(lightLevel1, lightLevel2);
    }
}
