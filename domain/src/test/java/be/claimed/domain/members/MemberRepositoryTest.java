package be.claimed.domain.members;

import be.claimed.configuration.Config;
import be.claimed.domain.addresses.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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

        org.assertj.core.api.Assertions.assertThat(actualMember.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(actualMember.getAddress().getStreetName()).isEqualTo("Somewhere Street");

    }


}