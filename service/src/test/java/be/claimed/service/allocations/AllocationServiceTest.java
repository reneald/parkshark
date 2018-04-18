package be.claimed.service.allocations;

import be.claimed.MockitoExtension;
import be.claimed.domain.allocations.Allocation;
import be.claimed.domain.allocations.AllocationRepository;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import be.claimed.domain.members.licensePlates.LicensePlate;
import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import be.claimed.service.parkingLots.ParkingLotService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AllocationServiceTest {

    @Mock
    AllocationRepository allocationRepository;
    @Mock
    static Allocation firstAllocation;

    List<Allocation> allocations;

    @Mock
    MemberRepository memberRepository;
    @Mock
    static Member firstMember;
    @Mock
    static Member secondMember;
    @Mock
    static Member thirdMember;

    List<Member> members;

    @Mock
    static LicensePlate firstLicensePlate;
    @Mock
    static LicensePlate secondLicensePlate;
    List<LicensePlate> licensePlates;
    @Mock
    static ParkingLot parkingLotCapacity400;
    @Mock
    static ParkingLot parkingLotCapacity1;
    @Mock
    static ParkingLot illegalParkingLot;

    List<ParkingLot> parkingLots;
    @Mock
    ParkingLotRepository parkingLotRepository;

    @InjectMocks
    AllocationService allocationService;

    @BeforeEach
    void setUp() {
        allocations = new ArrayList<>();
        allocations.add(firstAllocation);
        members = new ArrayList<>();
        members.add(firstMember);
        members.add(secondMember);
        licensePlates = new ArrayList<>();
        licensePlates.add(firstLicensePlate);
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLotCapacity400);
        parkingLots.add(parkingLotCapacity1);

        when(firstAllocation.getParkingLot()).thenReturn(parkingLotCapacity1);
        when(parkingLotCapacity400.getCapacity()).thenReturn(400);
        when(parkingLotCapacity1.getCapacity()).thenReturn(1);
        when(allocationRepository.create(firstAllocation)).thenReturn(firstAllocation);
        when(allocationRepository.getAll(Allocation.class)).thenReturn(allocations);
        when(memberRepository.getAll(Member.class)).thenReturn(members);
        when(parkingLotRepository.getAll(ParkingLot.class)).thenReturn(parkingLots);
    }

    @Test
    void create_shouldCallRepositoryMethod() {
        //GIVEN
        Allocation testAllocation = AllocationTestBuilder.legalAllocation().build();
        //WHEN
        allocationService.create(testAllocation);
        //THEN
        verify(allocationRepository, times(1)).create(testAllocation);
    }

    @Test
    void create_shouldSetStartTime() {
        //GIVEN
        Allocation testAllocation = AllocationTestBuilder.legalAllocation().build();
        LocalDateTime now = LocalDateTime.now();
        //WHEN
        allocationService.create(testAllocation, now);
        //THEN
        assertThat(testAllocation.getStartTime()).isEqualTo(now);
    }

    @Test
    void create_whenGivenIllegalMember_shouldThrowException() {
        //GIVEN
        when(firstAllocation.getMember()).thenReturn(thirdMember);
        //WHEN&THEN
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(firstAllocation))
                .withMessage("Member unknown");
    }

    @Test
    void create_whenGivenIllegalLicensePlate_shouldThrowException() {
        //GIVEN
        Allocation testAllocation = AllocationTestBuilder.allocationWithIllegalLicensePlate().build();

        //WHEN&THEN
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(testAllocation))
                .withMessage("This license plate is not registered to this member!");
    }

    @Test
    void create_whenGivenIllegalParkingLot_shouldThrowException() {
        //GIVEN
        Allocation testAllocation = AllocationTestBuilder.allocationWithIllegalParkingLot().build();
        //WHEN&THEN
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(testAllocation))
                .withMessage("This is NOT a ParkShark parking lot");
    }

    @Test
    void create_whenGivenParkingLotWithNoFreeSpaces_shouldThrowException() {
        //GIVEN
        Allocation testAllocation = AllocationTestBuilder.allocationWithParkingLotOfSize1().build();
        //WHEN&THEN
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(testAllocation))
                .withMessage("Sorry, this parking lot has no free spaces right now.");
    }

}