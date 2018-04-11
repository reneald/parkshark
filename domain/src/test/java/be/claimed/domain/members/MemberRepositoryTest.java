package be.claimed.domain.members;

import be.claimed.configuration.Config;
import be.claimed.domain.addresses.Address;
import be.claimed.domain.members.emails.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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
        Address memberAddress = Address.AddressBuilder.address().withStreetName("Somewhere Street").withStreetNumber("42").build();
        Member memberToAdd = Member.MemberBuilder.member().withFirstName("Lupe").withLastName("Fiasco").withAddress(memberAddress).build();

        Member actualMember = memberRepository.create(memberToAdd);

        assertThat(actualMember.getId()).isNotNull();
        assertThat(actualMember.getAddress().getStreetName()).isEqualTo("Somewhere Street");

    }

    @Test
    void create_whenGivenEmailAddress_shouldPersistEmailAddressAndMemberInDataBase() {
        Email email = Email.EmailBuilder.email().withEmail("mqlksjdfoisqu").build();
        Member cloud = Member.MemberBuilder.member().withFirstName("Cloud").withLastName("Strife").withEmail(email).build();

        Member actualMember = memberRepository.create(cloud);

        //assertThat(actualMember.getEmail()).isEqualTo(email);
        assertThat(actualMember.getEmail().getId()).isNotNull();

    }


}