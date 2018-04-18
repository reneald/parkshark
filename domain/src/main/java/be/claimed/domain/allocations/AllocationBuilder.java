package be.claimed.domain.allocations;

import be.claimed.domain.abstracts.AbstractBuilder;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.licensePlates.LicensePlate;
import be.claimed.domain.parkinglots.ParkingLot;

import java.time.LocalDateTime;
import java.util.UUID;

public class AllocationBuilder extends AbstractBuilder<Allocation> {
    private UUID id;
    private Member member;
    private LicensePlate licensePlate;
    private ParkingLot parkingLot;
    private LocalDateTime startTime;

    public static AllocationBuilder allocation() {
        return new AllocationBuilder();
    }

    public Allocation build() {
        return new Allocation(this);
    }

    public AllocationBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public AllocationBuilder withMember(Member member) {
        this.member = member;
        return this;
    }

    public AllocationBuilder withLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public AllocationBuilder withParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        return this;
    }

    public AllocationBuilder withStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public UUID getId() {
        return id;
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
}
