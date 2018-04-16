package be.claimed.api.members;

import be.claimed.api.AbstractMapper;
import be.claimed.api.addresses.AddressMapper;
import be.claimed.api.members.emails.EmailMapper;
import be.claimed.api.members.licensePlates.LicensePlateMapper;
import be.claimed.api.members.phoneNumbers.PhoneNumberMapper;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.membershiplevel.MembershipLevel;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Named
public class MemberMapper extends AbstractMapper<MemberDto, Member> {

    private AddressMapper addressMapper;
    private EmailMapper emailMapper;
    private PhoneNumberMapper phoneNumberMapper;
    private LicensePlateMapper licensePlateMapper;

    @Inject
    public MemberMapper(AddressMapper addressMapper, EmailMapper emailMapper, PhoneNumberMapper phoneNumberMapper, LicensePlateMapper licensePlateMapper) {
        this.addressMapper = addressMapper;
        this.emailMapper = emailMapper;
        this.phoneNumberMapper = phoneNumberMapper;
        this.licensePlateMapper = licensePlateMapper;
    }

    @Override
    public MemberDto toDto(Member domainObject) {
        MemberDto dtoObject = new MemberDto();
        dtoObject.id = domainObject.getId().toString();
        dtoObject.firstName = domainObject.getFirstName();
        dtoObject.lastName = domainObject.getLastName();
        dtoObject.addressDto = domainObject.getAddress() == null ? null : addressMapper.toDto(domainObject.getAddress());
        dtoObject.emailDto = domainObject.getEmail() == null ? null : emailMapper.toDto(domainObject.getEmail());
        dtoObject.phoneNumbers = domainObject.getPhoneNumbers() == null ? null : domainObject.getPhoneNumbers().stream()
                .map(phoneNumber -> phoneNumberMapper.toDto(phoneNumber))
                .collect(Collectors.toList());
        dtoObject.licensePlates = domainObject.getLicensePlates() == null ? null : domainObject.getLicensePlates().stream()
                .map(licensePlate -> licensePlateMapper.toDto(licensePlate))
                .collect(Collectors.toList());
        dtoObject.registrationDate = domainObject.getRegistrationDate().format(DateTimeFormatter.BASIC_ISO_DATE);
        dtoObject.membershipLevel = domainObject.getMembershipLevel().getName();

        return dtoObject;
    }

    @Override
    public Member toDomain(MemberDto dtoObject) {
        //TODO move member registration date creation to service
        Member domainObject = Member.MemberBuilder.member()
                .withFirstName(dtoObject.firstName)
                .withLastName(dtoObject.lastName)
                .withAddress(dtoObject.addressDto == null ? null : addressMapper.toDomain(dtoObject.addressDto))
                .withEmail(dtoObject.emailDto == null ? null : emailMapper.toDomain(dtoObject.emailDto))
                .withPhoneNumbers(dtoObject.phoneNumbers == null ? null : dtoObject.phoneNumbers.stream()
                        .map(phoneNumberDto -> phoneNumberMapper.toDomain(phoneNumberDto))
                        .collect(Collectors.toList()))
                .withLicensePlate(dtoObject.licensePlates == null ? null : dtoObject.licensePlates.stream()
                        .map(licensePlateDto -> licensePlateMapper.toDomain(licensePlateDto))
                        .collect(Collectors.toList()))
                .withMembershipLevel(dtoObject.membershipLevel == null ? null :MembershipLevel.valueOf(dtoObject.membershipLevel.toUpperCase()))
                .build();

        return domainObject;
    }
}
