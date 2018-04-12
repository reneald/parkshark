package be.claimed.api.members.phoneNumbers;

import be.claimed.api.AbstractMapper;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;

import javax.inject.Named;

@Named
public class PhoneNumberMapper extends AbstractMapper<PhoneNumberDto, PhoneNumber> {
    @Override
    public PhoneNumberDto toDto(PhoneNumber domainObject) {
        PhoneNumberDto dtoObject =  new PhoneNumberDto();
        dtoObject.id = domainObject.getId().toString();
        dtoObject.countryPrefix = domainObject.getCountryPrefix();
        dtoObject.number = domainObject.getNumber();
        return dtoObject;
    }

    @Override
    public PhoneNumber toDomain(PhoneNumberDto dtoObject) {
        PhoneNumber domainObject = PhoneNumber.PhoneNumberBuilder.phoneNumber()
                .withCountryPrefix(dtoObject.countryPrefix)
                .withNumber(dtoObject.number)
                .build();
        return domainObject;
    }
}
