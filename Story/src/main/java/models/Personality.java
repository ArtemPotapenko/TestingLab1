package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Personality {
    NERVOUS("нервничает"), PURPOSEFULNESS("демонстрирует целеустремленость"),
    KIND("демонстрирует доброту"), SMART("демонстрирует ум");
    private String description;
}
