package be.claimed.domain.members;

import be.claimed.configuration.Config;
import be.claimed.domain.addresses.Address;
import be.claimed.domain.addresses.PostCode;
import be.claimed.domain.members.emails.Email;
import be.claimed.domain.members.licensePlates.LicensePlate;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig (Config.class)
class MemberRepositoryTest {

    private MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    void create_shouldStoreMemberInDataBase(){
        Address memberAddress = Address.AddressBuilder.address()
                .withStreetName("Somewhere Street")
                .withStreetNumber("42")
                .withPostCode(PostCode.PostCodeBuilder.postCode().withPostCode("2850").withLabel("Boom").build())
                .build();
        Member memberToAdd = Member.MemberBuilder.member()
                .withFirstName("Lupe")
                .withLastName("Fiasco")
                .withAddress(memberAddress)
                .withRegistrationDate(LocalDate.now())
                .build();

        Member actualMember = memberRepository.create(memberToAdd);

        assertThat(actualMember.getId()).isNotNull();
        assertThat(actualMember.getAddress().getStreetName()).isEqualTo("Somewhere Street");

    }

    @Test
    void create_whenGivenEmailAddress_shouldPersistEmailAddressAndMemberInDataBase() {
        Email email = Email.EmailBuilder.email().withEmail("mqlksjdfoisqu@gmail.com").build();
        Member cloud = Member.MemberBuilder.member()
                .withFirstName("Cloud")
                .withLastName("Strife")
                .withEmail(email)
                .withRegistrationDate(LocalDate.now())
                .build();

        Member actualMember = memberRepository.create(cloud);

        assertThat(actualMember.getEmail().getId()).isNotNull();

    }

    @Test
    void create_whenGivenLicensePlates_shouldPersistLicensePlatesAndMembersInDataBase(){
        LicensePlate licensePlateOne = LicensePlate.LicensePlateBuilder.licensePlate().withIssuingCountry("Belgium").withLicensePlate("blabla").build();
        LicensePlate licensePlateTwo = LicensePlate.LicensePlateBuilder.licensePlate().withIssuingCountry("Belgium").withLicensePlate("stuff").build();
        List<LicensePlate> licensePlates = new ArrayList<>();
        licensePlates.add(licensePlateOne);
        licensePlates.add(licensePlateTwo);
        Member cloud = Member.MemberBuilder.member().withFirstName("Cloud").withLastName("Strife")
                .withRegistrationDate(LocalDate.now())
                .withLicensePlate(licensePlates).build();

        Member actualMember = memberRepository.create(cloud);

        assertThat(actualMember.getLicensePlates()).contains(licensePlateOne, licensePlateTwo);
    }

    @Test
    void create_whenGivenPhoneNumbers_shouldPersistPhoneNumbersAndMembersInDataBase() {
        PhoneNumber phoneNumber1 = PhoneNumber.PhoneNumberBuilder.phoneNumber().withCountryPrefix("+32").withNumber("465798956").build();
        PhoneNumber phoneNumber2 = PhoneNumber.PhoneNumberBuilder.phoneNumber().withCountryPrefix("+32").withNumber("465798965").build();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber1);
        phoneNumbers.add(phoneNumber2);
        Member cloud = Member.MemberBuilder.member().withFirstName("Cloud").withLastName("Strife")
                .withRegistrationDate(LocalDate.now())
                .withPhoneNumbers(phoneNumbers).build();

        Member actualMember = memberRepository.create(cloud);

        assertThat(actualMember.getPhoneNumbers()).contains(phoneNumber1, phoneNumber2);
    }


}