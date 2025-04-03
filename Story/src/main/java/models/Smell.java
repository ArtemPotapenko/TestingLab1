package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Smell {
    DECAY("запах тления"),
    STINK("вонь"),
    FLOWERS("аромат цветов");

    private final String name;
}
