package be.claimed.api.parkingLots;

import be.claimed.api.AbstractMapper;
import be.claimed.api.addresses.AddressMapper;
import be.claimed.api.contactPersons.ContactPersonMapper;
import be.claimed.api.divisions.DivisionMapper;
import be.claimed.domain.parkinglots.BuildingType;
import be.claimed.domain.parkinglots.ParkingLot;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
public class ParkingLotMapper extends AbstractMapper<ParkingLotDto, ParkingLot>{

    private ContactPersonMapper contactPersonMapper;
    private AddressMapper addressMapper;
    private DivisionMapper divisionMapper;

    @Inject
    public ParkingLotMapper(ContactPersonMapper contactPersonMapper, AddressMapper addressMapper, DivisionMapper divisionMapper) {
        this.contactPersonMapper = contactPersonMapper;
        this.addressMapper = addressMapper;
        this.divisionMapper = divisionMapper;
    }

    @Override
    public ParkingLotDto toDto(ParkingLot domainObject) {
        ParkingLotDto dtoObject = new ParkingLotDto();
        dtoObject.id = domainObject.getId().toString();
        dtoObject.name = domainObject.getName();
        dtoObject.divisionDto = domainObject.getDivision() == null ? null : divisionMapper.toDto(domainObject.getDivision());
        dtoObject.buildingTypeDto = domainObject.getBuildingType().toString();
        dtoObject.capacity = domainObject.getCapacity();
        dtoObject.pricePerHour = domainObject.getPricePerHour().doubleValue();
        dtoObject.contactPersonDto = domainObject.getContactPerson() == null ? null : contactPersonMapper.toDto(domainObject.getContactPerson());
        dtoObject.addressDto = domainObject.getAddress() == null ? null : addressMapper.toDto(domainObject.getAddress());
        return dtoObject;
    }
    @Override
    public ParkingLot toDomain(ParkingLotDto dtoObject) {
        ParkingLot domainObject = ParkingLot.ParkingLotBuilder.parkingLot()
                .withName(dtoObject.name)
                .withDivision(dtoObject.divisionDto == null ? null : divisionMapper.toDomain(dtoObject.divisionDto))
                .withBuildingType(dtoObject.buildingTypeDto == null ? null : BuildingType.valueOf(dtoObject.buildingTypeDto))
                .withCapacity(dtoObject.capacity)
                .withPricePerHour(BigDecimal.valueOf(dtoObject.pricePerHour))
                .withContactPerson(dtoObject.contactPersonDto == null ? null : contactPersonMapper.toDomain(dtoObject.contactPersonDto))
                .withAddress(dtoObject.addressDto == null ? null : addressMapper.toDomain(dtoObject.addressDto))
                .build();
        return domainObject;
    }

}
