package be.claimed.api.members;

import be.claimed.api.addresses.AddressDto;
import be.claimed.api.members.emails.EmailDto;
import be.claimed.api.members.licensePlates.LicensePlateDto;
import be.claimed.api.members.phoneNumbers.PhoneNumberDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MemberDto {

    public UUID id;
    public String firstName;
    public String lastName;
    public AddressDto addressDto;
    public EmailDto emailDto;
    public List<PhoneNumberDto> phoneNumbers;
    public List<LicensePlateDto> licensePlates;
    public LocalDate registrationDate;

    public MemberDto() {
    }
}
