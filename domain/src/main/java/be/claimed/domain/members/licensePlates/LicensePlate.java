package be.claimed.domain.members.licensePlates;

import be.claimed.domain.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "license_plates")
public class LicensePlate extends AbstractEntity {

    @Column(name = "license_plate_number")
    private String licensePlate;

    @Column(name = "issuing_country")
    private String issuingCountry;

//    @Column(name = "fk_member_id")
//    private UUID memberID;

    public LicensePlate(UUID id) {
        super(id);
    }

    public LicensePlate(LicensePlateBuilder licensePlateBuilder) {
        super(licensePlateBuilder.id);
        this.licensePlate = licensePlateBuilder.licensePlate;
        this.issuingCountry = licensePlateBuilder.issuingCountry;
        //this.memberID = licensePlateBuilder.memberID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

//    public UUID getMemberID() {
//        return memberID;
//    }

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

//        public LicensePlateBuilder withMemberID(UUID memberID) {
//            this.memberID = memberID;
//        return this;
//        }
    }
}
