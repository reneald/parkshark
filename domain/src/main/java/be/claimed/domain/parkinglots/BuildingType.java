package be.claimed.domain.parkinglots;

import javax.persistence.Embeddable;

public enum BuildingType {
    ABOVE_GROUND,
    UNDERGROUND;

    private String name;

    BuildingType() {
    }

}
