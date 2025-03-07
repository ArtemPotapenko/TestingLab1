package models;

import exception.DuplicatePersonalityException;
import exception.InvalidSpaceException;
import interfaces.Sniffable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class Person {
    private String name;
    private final Set<Personality> personalities = new HashSet<>();
    private final Set<Personality> fakePersonalities = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public String goByPlace(Place place, double space) throws InvalidSpaceException {
        if (space < 0) {
            throw new InvalidSpaceException("Скорость не может быть отрицательной");
        }
        if (Double.isNaN(space)) {
            throw new InvalidSpaceException("Скорость не может быть NaN");
        }
        if (Double.isInfinite(space)) {
            throw new InvalidSpaceException("Скорость не может быть NaN");
        }
        if (space < 3000) {
            return name + " идет по " + place.getPlaceName();
        } else {
            return name + " бежит по " + place.getPlaceName();
        }
    }

    public String sniff(Sniffable sniffable) {
        return name + " чувствует " + sniffable.getSniff().getName();
    }

    public void addPersonality(Personality personality) throws DuplicatePersonalityException {
        if (fakePersonalities.contains(personality)) {
            throw new DuplicatePersonalityException("Качество не может настоящим и фейковым");
        }
        personalities.add(personality);
    }
    public void addFakePersonality(Personality personality) throws DuplicatePersonalityException {
        if (personalities.contains(personality)) {
            throw new DuplicatePersonalityException("Качество не может настоящим и фейковым");
        }
        fakePersonalities.add(personality);
    }

    public String[] demonstratePersonality(){
        List<String> description = new ArrayList<>();
        for (Personality personality : personalities) {
            description.add(name + " " + personality.getDescription());
        }
        for (Personality personality : fakePersonalities) {
            description.add(name + " искуственно " + personality.getDescription());
        }
        return description.toArray(new String[0]);
    }

    public String[] touchWall(Wall wall){
        List<String> description = new ArrayList<>();
        description.add(name + " видит " + wall.getDescription() + " стену");
        description.add(name + " трогает стену");
        if (wall.getTemperature() < 10){
            description.add("Стена холодная");
        }
        if (wall.getTemperature() >= 10){
            description.add("Стена теплая");
        }
        return description.toArray(new String[0]);
    }

}
