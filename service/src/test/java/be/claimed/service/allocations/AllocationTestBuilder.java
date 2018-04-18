package be.claimed.service.allocations;

import be.claimed.domain.allocations.Allocation;
import be.claimed.domain.allocations.AllocationBuilder;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.licensePlates.LicensePlate;
import be.claimed.domain.parkinglots.ParkingLot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class AllocationTestBuilder {
    private UUID id;
    private Member member;
    private LicensePlate licensePlate;
    private ParkingLot parkingLot;
    private LocalDateTime startTime;

    private AllocationBuilder allocationBuilder;

    private AllocationTestBuilder() {
        allocationBuilder = AllocationBuilder.allocation();
    }

    public static AllocationTestBuilder legalAllocation() {
        List<LicensePlate> licensePlates = new ArrayList<>();
        licensePlates.add(AllocationServiceTest.firstLicensePlate);
        when(AllocationServiceTest.firstMember.getLicensePlates()).thenReturn(licensePlates);
        return allocation()
                .withMember(AllocationServiceTest.firstMember)
                .withLicensePlate(AllocationServiceTest.firstLicensePlate)
                .withParkingLot(AllocationServiceTest.parkingLotCapacity400);
    }

    public static AllocationTestBuilder allocationWithIllegalLicensePlate() {
        List<LicensePlate> licensePlates = new ArrayList<>();
        licensePlates.add(AllocationServiceTest.firstLicensePlate);
        return allocation()
                .withMember(AllocationServiceTest.firstMember)
                .withLicensePlate(AllocationServiceTest.secondLicensePlate);
    }

    public static AllocationTestBuilder allocationWithIllegalParkingLot() {
        List<LicensePlate> licensePlates = new ArrayList<>();
        licensePlates.add(AllocationServiceTest.firstLicensePlate);
        when(AllocationServiceTest.firstMember.getLicensePlates()).thenReturn(licensePlates);
        return allocation()
                .withMember(AllocationServiceTest.firstMember)
                .withLicensePlate(AllocationServiceTest.firstLicensePlate)
                .withParkingLot(AllocationServiceTest.illegalParkingLot);
    }

    public static AllocationTestBuilder allocationWithParkingLotOfSize1() {
        List<LicensePlate> licensePlates = new ArrayList<>();
        licensePlates.add(AllocationServiceTest.firstLicensePlate);
        when(AllocationServiceTest.firstMember.getLicensePlates()).thenReturn(licensePlates);
        return allocation()
                .withMember(AllocationServiceTest.firstMember)
                .withLicensePlate(AllocationServiceTest.firstLicensePlate)
                .withParkingLot(AllocationServiceTest.parkingLotCapacity1);
    }

    public static AllocationTestBuilder allocation() {
        return new AllocationTestBuilder();
    }

    public Allocation build() {
        return allocationBuilder.build();
    }

    public AllocationTestBuilder withId(UUID id) {
        allocationBuilder.withId(id);
        return this;
    }

    public AllocationTestBuilder withMember(Member member) {
        allocationBuilder.withMember(member);
        return this;
    }

    public AllocationTestBuilder withLicensePlate(LicensePlate licensePlate) {
        allocationBuilder.withLicensePlate(licensePlate);
        return this;
    }

    public AllocationTestBuilder withParkingLot(ParkingLot parkingLot) {
        allocationBuilder.withParkingLot(parkingLot);
        return this;
    }

    public AllocationTestBuilder withStartTime(LocalDateTime startTime) {
        allocationBuilder.withStartTime(startTime);
        return this;
    }
}
