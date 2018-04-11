package be.claimed.api.members.licensePlates;

import be.claimed.api.AbstractMapper;
import be.claimed.domain.members.licensePlates.LicensePlate;

import javax.inject.Named;

@Named
public class LicensePlateMapper extends AbstractMapper<LicensePlateDto, LicensePlate> {
    @Override
    public LicensePlateDto toDto(LicensePlate domainObject) {
        LicensePlateDto dtoObject = new LicensePlateDto();
        dtoObject.id = domainObject.getId();
        dtoObject.licensePlate = domainObject.getLicensePlate();
        dtoObject.issuingCountry = domainObject.getIssuingCountry();
        return dtoObject;
    }

    @Override
    public LicensePlate toDomain(LicensePlateDto dtoObject) {
        LicensePlate domainObject = LicensePlate.LicensePlateBuilder.licensePlate()
                .withLicensePlate(dtoObject.licensePlate)
                .withIssuingCountry(dtoObject.issuingCountry)
                .build();
        return domainObject;
    }
}
