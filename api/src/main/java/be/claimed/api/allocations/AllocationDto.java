package be.claimed.api.allocations;

import be.claimed.api.members.MemberDto;
import be.claimed.api.members.licensePlates.LicensePlateDto;
import be.claimed.api.parkingLots.ParkingLotDto;

public class AllocationDto {
    public String id;
    public MemberDto memberDto;
    public LicensePlateDto licensePlateDto;
    public ParkingLotDto parkingLotDto;
    public String startTime;

    public AllocationDto() {

    }
}
