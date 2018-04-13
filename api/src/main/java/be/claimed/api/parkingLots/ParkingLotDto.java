package be.claimed.api.parkingLots;

import be.claimed.api.addresses.AddressDto;
import be.claimed.api.divisions.DivisionDto;
import be.claimed.api.contactPersons.ContactPersonDto;

public class ParkingLotDto {
    public String id;
    public String name;
    public DivisionDto divisionDto;
    public String buildingTypeDto;
    public Integer capacity;
    public Double pricePerHour;
    public ContactPersonDto contactPersonDto;
    public AddressDto addressDto;

    public ParkingLotDto(){
    }
}
