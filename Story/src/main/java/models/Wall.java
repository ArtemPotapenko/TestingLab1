package models;

import exception.InvalidTemperatureException;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Wall {
    private String description;
    private double temperature;
    private Material material;
    private double lightLevel;

    public Wall(String description, double temperature, Material material) throws InvalidTemperatureException {
        this.description = description;
        if (Double.isNaN(temperature) || Double.isInfinite(temperature) || temperature < -100 || temperature > 100) {
            throw new InvalidTemperatureException("Некорректная температура: " + temperature);
        }
        this.temperature = temperature;
        this.material = material;
        this.lightLevel = 0;
    }

    public void heatUp(double lightIntensity) {
        temperature += lightIntensity * material.getHeatAbsorption();
        if (temperature > 100) {
            temperature = 100;
        }
    }

    public void coolDown(double time) {
        temperature -= time * material.getCoolingRate();
        if (temperature < -100) {
            temperature = -100;
        }
    }

    public void illuminate(double lightPower, double time) {
        lightLevel += lightPower * time * (1 - material.getLightAbsorption());
        if (lightLevel > 100) {
            lightLevel = 100;
        }
    }

    public boolean isVisible() {
        return lightLevel > 10;
    }
}