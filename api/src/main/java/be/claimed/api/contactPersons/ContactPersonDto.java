package be.claimed.api.contactPersons;

import be.claimed.api.addresses.AddressDto;
import be.claimed.api.members.emails.EmailDto;
import be.claimed.api.members.phoneNumbers.PhoneNumberDto;

import java.util.List;

public class ContactPersonDto {
    public String id;
    public String firstName;
    public String lastName;
    public List<PhoneNumberDto> phoneNumbers;
    public EmailDto emailDto;
    public AddressDto addressDto;

    public ContactPersonDto(){}
}
