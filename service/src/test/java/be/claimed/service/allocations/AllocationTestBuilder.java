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

import static be.claimed.service.allocations.AllocationServiceTest.firstMember;
import static org.mockito.Mockito.*;

public class AllocationTestBuilder {

    private AllocationBuilder allocationBuilder;
    private static List<LicensePlate> licensePlates;

    private AllocationTestBuilder() {
        allocationBuilder = AllocationBuilder.allocation();
    }

    private static void setUp() {
        licensePlates = new ArrayList<>();
        licensePlates.add(AllocationServiceTest.knownLicensePlate);
        when(firstMember.getLicensePlates()).thenReturn(licensePlates);
    }

    static AllocationTestBuilder legalAllocation() {
        setUp();
        return allocation()
                .withMember(firstMember)
                .withLicensePlate(AllocationServiceTest.knownLicensePlate)
                .withParkingLot(AllocationServiceTest.parkingLotCapacity400);
    }

    static AllocationTestBuilder allocationWithUnknownLicensePlate() {
        setUp();
        return allocation()
                .withMember(firstMember)
                .withLicensePlate(AllocationServiceTest.unknownLicensePlate);
    }

    static AllocationTestBuilder allocationWithIllegalParkingLot() {
        setUp();
        return allocation()
                .withMember(firstMember)
                .withLicensePlate(AllocationServiceTest.knownLicensePlate)
                .withParkingLot(AllocationServiceTest.illegalParkingLot);
    }

    static AllocationTestBuilder allocationWithParkingLotOfSize1() {
        setUp();
        return allocation()
                .withMember(firstMember)
                .withLicensePlate(AllocationServiceTest.knownLicensePlate)
                .withParkingLot(AllocationServiceTest.parkingLotCapacity1);
    }

    static AllocationTestBuilder allocation() {
        return new AllocationTestBuilder();
    }

    Allocation build() {
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
