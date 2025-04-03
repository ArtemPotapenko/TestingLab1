package models;

import exception.DuplicatePersonalityException;
import exception.InvalidSpaceException;
import interfaces.Sniffable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class Person {
    private String name;
    private final Set<Personality> personalities = new HashSet<>();
    private final Set<Personality> fakePersonalities = new HashSet<>();
    private double speed;
    private Position position;
    private double height;


    public Person(String name) {
        this.name = name;
        this.speed = 1;
    }

    public String goByPlace(Place place) throws InvalidSpaceException {
        if (speed < 0 || Double.isNaN(speed) || Double.isInfinite(speed)) {
            throw new InvalidSpaceException("Некорректная скорость");
        }
        return speed < 3000 ? name + " идет по " + place.getPlaceName() : name + " бежит по " + place.getPlaceName();
    }

    public void adjustSpeed(double factor) {
        this.speed = factor;
    }

    public String sniff(Sniffable sniffable) {
        return name + " чувствует " + sniffable.getSniff().getName();
    }

    public void addPersonality(Personality personality) throws DuplicatePersonalityException {
        if (fakePersonalities.contains(personality)) {
            throw new DuplicatePersonalityException("Качество не может быть настоящим и фейковым");
        }
        personalities.add(personality);
    }

    public void addFakePersonality(Personality personality) throws DuplicatePersonalityException {
        if (personalities.contains(personality)) {
            throw new DuplicatePersonalityException("Качество не может быть настоящим и фейковым");
        }
        fakePersonalities.add(personality);
    }

    public String[] demonstratePersonality() {
        List<String> description = new ArrayList<>();
        for (Personality personality : personalities) {
            description.add(name + " " + personality.getDescription());
        }
        for (Personality personality : fakePersonalities) {
            description.add(name + " искусственно " + personality.getDescription());
        }
        return description.toArray(new String[0]);
    }

    public String[] touchWall(Wall wall) {
        List<String> description = new ArrayList<>();
        description.add(name + " видит " + wall.getDescription() + " стену");
        description.add(name + " трогает стену");
        description.add(wall.getTemperature() < 10 ? "Стена холодная" : "Стена теплая");
        return description.toArray(new String[0]);
    }

    public String shineFlashlight(Wall wall, double lightPower, double time) {
        wall.illuminate(lightPower, time);
        return name + " светит фонарем, освещая стену. Теперь ее видно на " + wall.getLightLevel() + "%";
    }

    public String sniffAround(Place place) {
        Smell strongestSmell = place.getStrongestSmell();
        return strongestSmell != null ? name + " чувствует " + strongestSmell.getName() : name + " не чувствует запахов.";
    }
}
