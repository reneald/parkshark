package be.claimed.api.allocations;

import be.claimed.api.AbstractMapper;
import be.claimed.api.members.MemberMapper;
import be.claimed.api.members.licensePlates.LicensePlateMapper;
import be.claimed.api.parkingLots.ParkingLotMapper;
import be.claimed.domain.allocations.Allocation;

import javax.inject.Named;

@Named
public class AllocationMapper extends AbstractMapper<AllocationDto, Allocation> {
    private MemberMapper memberMapper;
    private LicensePlateMapper licensePlateMapper;
    private ParkingLotMapper parkingLotMapper;

    public AllocationMapper(MemberMapper memberMapper, LicensePlateMapper licensePlateMapper, ParkingLotMapper parkingLotMapper) {
        this.memberMapper = memberMapper;
        this.licensePlateMapper = licensePlateMapper;
        this.parkingLotMapper = parkingLotMapper;
    }

    @Override
    public AllocationDto toDto(Allocation domainObject) {
        AllocationDto dtoObject = new AllocationDto();
        dtoObject.id = domainObject.getId().toString();
        dtoObject.memberDto = domainObject.getMember() == null ? null : memberMapper.toDto(domainObject.getMember());
        dtoObject.licensePlateDto = domainObject.getLicensePlate() == null ? null : licensePlateMapper.toDto(domainObject.getLicensePlate());
        dtoObject.parkingLotDto = domainObject.getParkingLot() == null ? null : parkingLotMapper.toDto(domainObject.getParkingLot());
        dtoObject.startTime = domainObject.getStartTime() == null ? null : domainObject.getParkingLot().toString();

        return dtoObject;
    }

    @Override
    public Allocation toDomain(AllocationDto dtoObject) {
        Allocation domainObject = Allocation.AllocationBuilder.allocation()
                .withMember(dtoObject.memberDto == null ? null : memberMapper.toDomain(dtoObject.memberDto))
                .withLicensePlate(dtoObject.licensePlateDto == null ? null : licensePlateMapper.toDomain(dtoObject.licensePlateDto))
                .withParkingLot(dtoObject.parkingLotDto == null ? null : parkingLotMapper.toDomain(dtoObject.parkingLotDto))
                .build();
        return domainObject;
    }
}
