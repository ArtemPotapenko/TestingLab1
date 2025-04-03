package service;

import models.Lighting;
import models.Person;
import models.Position;
import models.WallLightingPosition;

import java.util.List;

public class PeopleService {
    public void run(Person person, int degree, int time) {
        Position position = person.getPosition();
        position.setY(position.getY() + Math.sin(degree * Math.PI / 180) * person.getSpeed() * time);
        position.setX(position.getX() + Math.cos(degree * Math.PI / 180) * person.getSpeed() * time);
        person.setPosition(position);
    }
    private boolean inCircle(double x, double y, double radius, WallLightingPosition wallLightingPosition) {
        return (wallLightingPosition.getX() - x) * (wallLightingPosition.getX() - x)  + (wallLightingPosition.getY() - y) * (wallLightingPosition.getY() - y) < radius * radius;
    }
    public void light(Person person, List<WallLightingPosition> wallLightingPositions, Lighting lighting) {
        Position position = person.getPosition();
        double circleX = position.getY();
        double circleY = person.getHeight() * 0.75;
        double radius = lighting.getRadius() + lighting.getRadiusFactor() * position.getX();
        double lightLevel = lighting.getPower() - lighting.getDistanceFactor() * position.getY();
        wallLightingPositions.stream().filter((wallLightingPosition) -> inCircle(circleX, circleY, radius, wallLightingPosition))
                .forEach((wallLightingPosition) -> {wallLightingPosition.setLightLevel(wallLightingPosition.getLightLevel() + lightLevel);});
    }
}
