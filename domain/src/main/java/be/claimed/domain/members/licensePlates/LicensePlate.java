package be.claimed.domain.members.licensePlates;

import be.claimed.domain.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "license_plates")
public class LicensePlate extends AbstractEntity {

    @Column(name = "license_plate_number")
    @NotNull(message = "country must be filled in")
    private String licensePlate;

    @Column(name = "issuing_country")
    @NotNull(message = "country must be filled in")
    private String issuingCountry;

    public LicensePlate(UUID id) {
        super(id);
    }

    public LicensePlate(LicensePlateBuilder licensePlateBuilder) {
        super(licensePlateBuilder.id);
        this.licensePlate = licensePlateBuilder.licensePlate;
        this.issuingCountry = licensePlateBuilder.issuingCountry;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }


    public static class LicensePlateBuilder {
        private UUID id;
        private String licensePlate;
        private String issuingCountry;

        public static LicensePlateBuilder licensePlate() {
            return new LicensePlateBuilder();
        }

        public LicensePlate build() {
            return new LicensePlate(this);
        }

        public LicensePlateBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public LicensePlateBuilder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public LicensePlateBuilder withIssuingCountry(String issuingCountry) {
            this.issuingCountry = issuingCountry;
            return this;
        }

    }
}
