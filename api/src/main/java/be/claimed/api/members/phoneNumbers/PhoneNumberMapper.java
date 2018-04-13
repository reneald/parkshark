package be.claimed.api.members.phoneNumbers;

import be.claimed.api.AbstractMapper;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;

import javax.inject.Named;

@Named
public class PhoneNumberMapper extends AbstractMapper<PhoneNumberDto, PhoneNumber> {
    @Override
    public PhoneNumberDto toDto(PhoneNumber domainObject) {
        PhoneNumberDto dtoObject =  new PhoneNumberDto();
        dtoObject.mobilePhoneNumber = domainObject.getMobilePhoneNumber();
        dtoObject.telephoneNumber = domainObject.getTelephoneNumber();
        return dtoObject;
    }

    @Override
    public PhoneNumber toDomain(PhoneNumberDto dtoObject) {
        PhoneNumber domainObject = PhoneNumber.PhoneNumberBuilder.phoneNumber()
                .withMobilePhoneNumber(dtoObject.mobilePhoneNumber)
                .withTelephoneNumber(dtoObject.telephoneNumber)
                .build();
        return domainObject;
    }
}
