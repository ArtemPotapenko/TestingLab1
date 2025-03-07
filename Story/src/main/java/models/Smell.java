package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Smell {
    DECAY("запах тления"), STINK("вонь"), FLOWERS("запах цветов");
    private String name;
}
