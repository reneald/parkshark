package be.claimed.domain.allocations;

import be.claimed.domain.abstracts.AbstractBuilder;
import be.claimed.domain.abstracts.AbstractEntity;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.licensePlates.LicensePlate;
import be.claimed.domain.parkinglots.ParkingLot;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "allocations")
public class Allocation extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "fk_member_id")
    @NotNull(message = "Please provide a member.")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "fk_license_plate_id")
    @NotNull(message = "Please provide a license plate.")
    private LicensePlate licensePlate;

    @ManyToOne
    @JoinColumn(name = "fk_parking_lot_id")
    @NotNull(message = "Please provide a parking lot.")
    private ParkingLot parkingLot;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    private Allocation() {

    }

    public Allocation(AllocationBuilder builder) {
        super(builder.getId());
        this.member = builder.getMember();
        this.licensePlate = builder.getLicensePlate();
        this.parkingLot = builder.getParkingLot();
        this.startTime = builder.getStartTime();
    }

    public Member getMember() {
        return member;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Allocation setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    //    public static class AllocationBuilder extends AbstractBuilder<Allocation> {
//        private UUID id;
//        private Member member;
//        private LicensePlate licensePlate;
//        private ParkingLot parkingLot;
//        private LocalDateTime startTime;
//
//        public static AllocationBuilder allocation() {
//            return new AllocationBuilder();
//        }
//
//        public Allocation build() {
//            return new Allocation(this);
//        }
//
//        public AllocationBuilder withId(UUID id) {
//            this.id = id;
//            return this;
//        }
//
//        public AllocationBuilder withMember(Member member) {
//            this.member = member;
//            return this;
//        }
//
//        public AllocationBuilder withLicensePlate(LicensePlate licensePlate) {
//            this.licensePlate = licensePlate;
//            return this;
//        }
//
//        public AllocationBuilder withParkingLot(ParkingLot parkingLot) {
//            this.parkingLot = parkingLot;
//            return this;
//        }
//
//        public AllocationBuilder withStartTime(LocalDateTime startTime) {
//            this.startTime = startTime;
//            return this;
//        }
//    }
}
