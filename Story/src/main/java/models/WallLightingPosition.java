package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallLightingPosition {
    private double x;
    private double y;
    private double lightLevel;
}
