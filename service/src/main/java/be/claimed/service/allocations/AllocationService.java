package be.claimed.service.allocations;

import be.claimed.domain.allocations.Allocation;
import be.claimed.domain.allocations.AllocationRepository;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class AllocationService {
    private AllocationRepository allocationRepository;
    private MemberRepository memberRepository;
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public AllocationService(AllocationRepository allocationRepository, MemberRepository memberRepository, ParkingLotRepository parkingLotRepository) {
        this.allocationRepository = allocationRepository;
        this.memberRepository = memberRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Allocation create(Allocation allocation) {
        return create(allocation, LocalDateTime.now());
    }

    public Allocation create(Allocation allocation, LocalDateTime startTime) {
        if (!memberRepository.getAll(Member.class).contains(allocation.getMember())) {
            throw new IllegalArgumentException("Member unknown");
        }
        if (!allocation.getMember().getLicensePlates().contains(allocation.getLicensePlate())) {
            throw new IllegalArgumentException("This license plate is not registered to this member!");
        }
        if (!parkingLotRepository.getAll(ParkingLot.class).contains(allocation.getParkingLot())) {
            throw new IllegalArgumentException("This is NOT a ParkShark parking lot");
        }
        allocation.setStartTime(startTime);
        return allocationRepository.create(allocation);
    }
}
