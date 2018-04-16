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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AllocationServiceTest {

    @Mock
    AllocationRepository allocationRepository;
    @Mock
    Allocation firstAllocation;
    @Mock
    Allocation secondAllocation;

    List<Allocation> allocations;

    @Mock
    MemberRepository memberRepository;
    @Mock
    Member firstMember;
    @Mock
    Member secondMember;
    @Mock
    Member thirdMember;
    List<Member> members;

    @Mock
    LicensePlate firstLicensePlate;
    @Mock
    LicensePlate secondLicensePlate;
    List<LicensePlate> licensePlates;
    @Mock
    ParkingLot firstParkingLot;
    @Mock
    ParkingLot secondParkingLot;
    List<ParkingLot> parkingLots;
    @Mock
    ParkingLotRepository parkingLotRepository;
    @Mock
    ParkingLotService parkingLotService;

    @InjectMocks
    AllocationService allocationService;

    @BeforeEach
    void setUp() {
        allocations = new ArrayList<>();
        allocations.add(firstAllocation);
        allocations.add(secondAllocation);
        members = new ArrayList<>();
        members.add(firstMember);
        members.add(secondMember);
        licensePlates = new ArrayList<>();
        licensePlates.add(firstLicensePlate);
        parkingLots = new ArrayList<>();
        parkingLots.add(secondParkingLot);

        when(allocationRepository.create(firstAllocation)).thenReturn(firstAllocation);
        when(allocationRepository.getAll(Allocation.class)).thenReturn(allocations);
        when(memberRepository.getAll(Member.class)).thenReturn(members);
    }

    @Test
    void create_shouldCallRepositoryMethod() {
        when(firstAllocation.getMember()).thenReturn(firstMember);
        when(firstMember.getLicensePlates()).thenReturn(licensePlates);
        when(firstAllocation.getLicensePlate()).thenReturn(firstLicensePlate);
        when(parkingLotRepository.getAll(ParkingLot.class)).thenReturn(parkingLots);
        when(firstAllocation.getParkingLot()).thenReturn(secondParkingLot);
        when(secondParkingLot.getCapacity()).thenReturn(400);
        List<Allocation> allocations = new ArrayList<>();
        allocations.add(firstAllocation);
        when(allocationRepository.getAll(Allocation.class)).thenReturn(allocations);
        allocationService.create(firstAllocation);

        verify(allocationRepository, times(1)).create(firstAllocation);
    }

    @Test
    void create_shouldSetStartTime() {
        when(firstAllocation.getMember()).thenReturn(firstMember);
        when(firstMember.getLicensePlates()).thenReturn(licensePlates);
        when(firstAllocation.getLicensePlate()).thenReturn(firstLicensePlate);
        when(parkingLotRepository.getAll(ParkingLot.class)).thenReturn(parkingLots);
        when(firstAllocation.getParkingLot()).thenReturn(secondParkingLot);
        when(secondParkingLot.getCapacity()).thenReturn(400);
        List<Allocation> allocations = new ArrayList<>();
        allocations.add(firstAllocation);
        when(allocationRepository.getAll(Allocation.class)).thenReturn(allocations);
        LocalDateTime now = LocalDateTime.now();
        allocationService.create(firstAllocation, now);

        verify(firstAllocation, times(1)).setStartTime(now);
    }

    @Test
    void create_whenGivenIllegalMember_shouldThrowException() {
        when(firstAllocation.getMember()).thenReturn(thirdMember);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(firstAllocation))
                .withMessage("Member unknown");
    }

    @Test
    void create_whenGivenIllegalLicensePlate_shouldThrowException() {
        when(firstAllocation.getMember()).thenReturn(firstMember);
        when(firstMember.getLicensePlates()).thenReturn(licensePlates);
        when(firstAllocation.getLicensePlate()).thenReturn(secondLicensePlate);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(firstAllocation))
                .withMessage("This license plate is not registered to this member!");
    }

    @Test
    void create_whenGivenIllegalParkingLot_shouldThrowException() {
        when(firstMember.getLicensePlates()).thenReturn(licensePlates);
        when(firstAllocation.getLicensePlate()).thenReturn(firstLicensePlate);
        when(firstAllocation.getMember()).thenReturn(firstMember);
        when(parkingLotRepository.getAll(ParkingLot.class)).thenReturn(parkingLots);
        when(firstAllocation.getParkingLot()).thenReturn(firstParkingLot);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(firstAllocation))
                .withMessage("This is NOT a ParkShark parking lot");
    }

    @Test
    void create_whenGivenParkingLotWithNoFreeSpaces_shouldThrowException() {
        when(firstMember.getLicensePlates()).thenReturn(licensePlates);
        when(firstAllocation.getLicensePlate()).thenReturn(firstLicensePlate);
        when(firstAllocation.getMember()).thenReturn(firstMember);
        when(secondParkingLot.getCapacity()).thenReturn(1);
        when(parkingLotRepository.getAll(ParkingLot.class)).thenReturn(parkingLots);
        when(firstAllocation.getParkingLot()).thenReturn(secondParkingLot);
        List<Allocation> allocations = new ArrayList<>();
        allocations.add(firstAllocation);
        when(allocationRepository.getAll(Allocation.class)).thenReturn(allocations);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> allocationService.create(firstAllocation))
                .withMessage("Sorry, this parking lot has no free spaces right now.");
    }

}