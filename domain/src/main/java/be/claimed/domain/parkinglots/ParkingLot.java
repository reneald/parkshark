package be.claimed.domain.parkinglots;

import be.claimed.domain.abstracts.AbstractBuilder;
import be.claimed.domain.abstracts.AbstractEntity;
import be.claimed.domain.addresses.Address;
import be.claimed.domain.contactPersons.ContactPerson;
import be.claimed.domain.divisions.Division;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "parking_lots")
public class ParkingLot extends AbstractEntity {

    @Column(name = "name")
    @NotNull(message = "Please provide a name")
    private String name;

    @OneToOne ()
    @JoinColumn (name = "fk_division_id")
    @NotNull
    private Division division;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BuildingType buildingType;

    @Column(name = "capacity")
    @NotNull
    private Integer capacity;

    @Column(name = "price_per_hour")
    @NotNull
    private BigDecimal pricePerHour;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "fk_contact_person_id")
    @NotNull
    private ContactPerson contactPerson;

    @Embedded
    @NotNull
    private Address address;

    private ParkingLot() {
    }

    public ParkingLot setDivision(Division division) {
        this.division = division;
        return this;
    }

    public ParkingLot(ParkingLotBuilder parkingLotBuilder) {
        super(parkingLotBuilder.id);
        this.name = parkingLotBuilder.name;
        this.division = parkingLotBuilder.division;
        this.buildingType = parkingLotBuilder.buildingType;
        this.capacity = parkingLotBuilder.capacity;
        this.pricePerHour = parkingLotBuilder.pricePerHour;
        this.contactPerson = parkingLotBuilder.contactPerson;
        this.address = parkingLotBuilder.address;
    }

    public String getName() {
        return name;
    }

    public Division getDivision() {
        return division;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public static class ParkingLotBuilder extends AbstractBuilder<ParkingLot> {
        private UUID id;
        private String name;
        private Division division;
        private BuildingType buildingType;
        private Integer capacity;
        private BigDecimal pricePerHour;
        private ContactPerson contactPerson;
        private Address address;

        public static ParkingLotBuilder parkingLot() {
            return new ParkingLotBuilder();
        }

        public ParkingLot build() {
            return new ParkingLot(this);
        }

        public ParkingLotBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ParkingLotBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ParkingLotBuilder withDivision(Division division) {
            this.division = division;
            return this;
        }

        public ParkingLotBuilder withBuildingType(BuildingType buildingType) {
            this.buildingType = buildingType;
            return this;
        }

        public ParkingLotBuilder withCapacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public ParkingLotBuilder withPricePerHour(BigDecimal pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        public ParkingLotBuilder withContactPerson(ContactPerson contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public ParkingLotBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }
    }
}
