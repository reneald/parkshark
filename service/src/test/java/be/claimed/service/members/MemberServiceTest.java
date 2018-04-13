package be.claimed.service.members;

import be.claimed.MockitoExtension;
import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private Member firstMember;

    @BeforeEach
    void setUp() {
        when(memberRepository.create(firstMember)).thenReturn(firstMember);
        memberService = new MemberService(memberRepository);
    }

    @Test
    void createMember_shouldCallRepositoryMethod() {
        memberService.createMember(firstMember);
        verify(memberRepository, times(1)).create(firstMember);
    }

}