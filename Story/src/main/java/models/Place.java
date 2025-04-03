package models;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Place {
    private String placeName;
    private Map<Smell, Double> smells = new HashMap<>();

    public Place(String placeName) {
        this.placeName = placeName;
    }

    public void addSmell(Smell smell, double intensity) {
        smells.put(smell, intensity);
    }

    public void dissipateSmells(double time) {
        smells.replaceAll((smell, intensity) -> Math.max(0, intensity - time * 0.1));
    }

    public Smell getStrongestSmell() {
        return smells.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
