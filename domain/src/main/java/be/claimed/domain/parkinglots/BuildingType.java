package be.claimed.domain.parkinglots;

import javax.persistence.Embeddable;

@Embeddable
public enum BuildingType {
    ABOVE_GROUND("Above Ground"),
    UNDERGROUND("Underground");

    private String label;

    BuildingType(String label) {
        this.label = label;
    }

    BuildingType() {
    }
}
