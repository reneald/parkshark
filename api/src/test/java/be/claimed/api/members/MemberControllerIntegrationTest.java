package be.claimed.api.members;

import be.claimed.api.ControllerIntegrationTest;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.inject.Inject;


public class MemberControllerIntegrationTest extends ControllerIntegrationTest<Member, MemberRepository> {

    @Inject
    private MemberController memberController;

    @Test
    void create() {
        MemberDto memberDto = new MemberDto();
        memberDto.firstName = "George";
        memberDto.lastName = "Clooney";

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
