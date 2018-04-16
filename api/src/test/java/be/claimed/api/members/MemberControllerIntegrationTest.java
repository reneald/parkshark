package be.claimed.api.members;

import be.claimed.api.ControllerIntegrationTest;
import be.claimed.api.members.phoneNumbers.PhoneNumberDto;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class MemberControllerIntegrationTest extends ControllerIntegrationTest<Member, MemberRepository> {


    @Test
    void create() {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.mobilePhoneNumber = "045564254";
        List<PhoneNumberDto> phoneNumberDtos = new ArrayList<>();
        phoneNumberDtos.add(phoneNumberDto);
        MemberDto memberDto = new MemberDto();
        memberDto.firstName = "George";
        memberDto.lastName = "Clooney";
        memberDto.phoneNumbers = phoneNumberDtos;

        MemberDto actualMemberDto = new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "members"), memberDto, MemberDto.class);

        Assertions.assertThat(actualMemberDto).isNotNull();
        Assertions.assertThat(actualMemberDto.id).isNotNull().isNotEmpty();


    }

    @Override
    @BeforeEach
    public void setUpDatabase() {
        clearDataBase(Member.class);
    }

    @AfterEach
    @Override
    public void breakDatabase() {
        clearDataBase(Member.class);
    }
}
