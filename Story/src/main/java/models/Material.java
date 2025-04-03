package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Material {
    METAL(0.5, 0.2, 0.8),
    STONE(0.3, 0.1, 0.5),
    WOOD(0.7, 0.05, 0.3),
    GLASS(0.9, 0.3, 0.1);

    private final double heatAbsorption;
    private final double coolingRate;
    private final double lightAbsorption;
}
