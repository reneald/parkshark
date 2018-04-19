package be.claimed.service.allocations;

import be.claimed.domain.allocations.Allocation;
import be.claimed.domain.allocations.AllocationRepository;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AllocationService {
    private AllocationRepository allocationRepository;
    private MemberRepository memberRepository;
    private ParkingLotRepository parkingLotRepository;
    private Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger("logger");

    @Autowired
    public AllocationService(AllocationRepository allocationRepository, MemberRepository memberRepository, ParkingLotRepository parkingLotRepository) {
        this.allocationRepository = allocationRepository;
        this.memberRepository = memberRepository;
        this.parkingLotRepository = parkingLotRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Allocation create(Allocation allocation) {
        return create(allocation, LocalDateTime.now());
    }

    public Allocation create(Allocation allocation, LocalDateTime startTime) {
        validate(allocation);
        allocation.setStartTime(startTime);
        return allocationRepository.create(allocation);
    }

    private void validate(Allocation allocation) {
        Set<ConstraintViolation<Allocation>> constraintViolations = validator.validate(allocation);
        constraintViolations.stream()
                .peek(violation -> LOGGER.error(violation.getMessage()))
                .close();
        if (!memberRepository.getAll(Member.class).contains(allocation.getMember())) {
            LOGGER.error("Member unknown");
            throw new IllegalArgumentException("Member unknown");
        }
        if (!allocation.getMember().getLicensePlates().contains(allocation.getLicensePlate())) {
            LOGGER.error("This license plate is not registered to this member!");
            throw new IllegalArgumentException("This license plate is not registered to this member!");
        }
        if (!parkingLotRepository.getAll(ParkingLot.class).contains(allocation.getParkingLot())) {
            LOGGER.error("This is NOT a ParkShark parking lot. The Acquisitions department has been notified.");
            throw new IllegalArgumentException("This is NOT a ParkShark parking lot. The Acquisitions department has been notified.");
        }
        if (getAmountOfOpenAllocationsByParkingLot(allocation.getParkingLot()) >= allocation.getParkingLot().getCapacity()) {
            LOGGER.error("Sorry, this parking lot has no free spaces right now.");
            throw new IllegalArgumentException("Sorry, this parking lot has no free spaces right now.");
        }
    }

    public Integer getAmountOfOpenAllocationsByParkingLot(ParkingLot parkingLot) {
        return getOpenAllocationsByParkingLot(parkingLot).size();
    }

    public List<Allocation> getOpenAllocationsByParkingLot(ParkingLot parkingLot) {
        return allocationRepository.getAll(Allocation.class).stream()
                .filter(allocation -> allocation.getParkingLot().equals(parkingLot))
                .collect(Collectors.toList());
    }
}
