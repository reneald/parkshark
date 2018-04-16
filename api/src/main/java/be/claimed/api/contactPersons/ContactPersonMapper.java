package be.claimed.api.contactPersons;

import be.claimed.api.AbstractMapper;
import be.claimed.api.addresses.AddressMapper;
import be.claimed.api.members.emails.EmailMapper;
import be.claimed.api.members.phoneNumbers.PhoneNumberMapper;
import be.claimed.domain.contactPersons.ContactPerson;

import javax.inject.Named;
import java.util.stream.Collectors;

@Named
public class ContactPersonMapper extends AbstractMapper<ContactPersonDto, ContactPerson> {

    private PhoneNumberMapper phoneNumberMapper;
    private EmailMapper emailMapper;
    private AddressMapper addressMapper;

    @Override
    public ContactPersonDto toDto(ContactPerson domainObject) {
        ContactPersonDto dtoObject = new ContactPersonDto();
        dtoObject.id = domainObject.getId().toString();
        dtoObject.firstName = domainObject.getFirstName();
        dtoObject.lastName = domainObject.getLastName();
        dtoObject.phoneNumbers = domainObject.getPhoneNumbers() == null ? null : domainObject.getPhoneNumbers().stream()
                .map(phoneNumber -> phoneNumberMapper.toDto(phoneNumber))
                .collect(Collectors.toList());
        dtoObject.emailDto = domainObject.getEmail() == null ? null : emailMapper.toDto(domainObject.getEmail());
        dtoObject.addressDto = domainObject.getAddress() == null ? null : addressMapper.toDto(domainObject.getAddress());
        return dtoObject;
    }

    @Override
    public ContactPerson toDomain(ContactPersonDto dtoObject) {
        ContactPerson domainObject = ContactPerson.ContactPersonBuilder.contactPerson()
                .withFirstName(dtoObject.firstName)
                .withLastName(dtoObject.lastName)
                .withPhoneNumbers(dtoObject.phoneNumbers == null ? null : dtoObject.phoneNumbers.stream()
                        .map(phoneNumberDto -> phoneNumberMapper.toDomain(phoneNumberDto))
                        .collect(Collectors.toList()))
                .withEmail(dtoObject.emailDto == null ? null : emailMapper.toDomain(dtoObject.emailDto))
                .withAddress(dtoObject.addressDto == null ? null : addressMapper.toDomain(dtoObject.addressDto))
                .build();
        return domainObject;
    }
}
