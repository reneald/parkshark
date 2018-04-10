package be.claimed.domain.members;

import be.claimed.domain.entities.AbstractEntity;

import java.util.UUID;

public class LicensePlate extends AbstractEntity {
    private String licensePlate;
    private String issuingCountry;
    private UUID memberID;

    public LicensePlate(LicensePlateBuilder licensePlateBuilder) {
        super(licensePlateBuilder.id);
        this.licensePlate = licensePlateBuilder.licensePlate;
        this.issuingCountry = licensePlateBuilder.issuingCountry;
        this.memberID = licensePlateBuilder.memberID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public UUID getMemberID() {
        return memberID;
    }

    public static class LicensePlateBuilder{
        private UUID id;
        private String licensePlate;
        private String issuingCountry;
        private UUID memberID;

        public static LicensePlateBuilder licensePlate(){
            return new LicensePlateBuilder();
        }

        public LicensePlate build(){
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

        public LicensePlateBuilder withMemberID(UUID memberID) {
            this.memberID = memberID;
        return this;
        }
    }
}
