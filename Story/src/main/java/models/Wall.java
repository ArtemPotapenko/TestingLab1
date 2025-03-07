package models;

import exception.InvalidSpaceException;
import exception.InvalidTemperatureException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Wall {
    private String description;
    private double temperature;

    public Wall(String description, double temperature) throws InvalidTemperatureException {
        this.description = description;
        if (Double.isNaN(temperature)){
          throw new InvalidTemperatureException("Температура не может быть NaN");
        }
        if (Double.isInfinite(temperature)){
            throw new InvalidTemperatureException("Температура не может быть бесконечной");
        }
        if (temperature < -100 || temperature > 100){
            throw new InvalidTemperatureException("Тепмпература не может иметь температуру %s".formatted(temperature));
        }
        this.temperature = temperature;
    }
}
