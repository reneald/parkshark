package be.claimed.service.members;

import be.claimed.domain.members.Member;
import be.claimed.domain.members.MemberRepository;
import be.claimed.domain.members.membershiplevel.MembershipLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember (Member member){
        setMembershipLevel(member);
        member.setRegistrationDate(LocalDate.now());
        return memberRepository.create(member);
    }

    private void setMembershipLevel(Member member) {
        if (member.getMembershipLevel() == null) {
            member.setMembershipLevel(MembershipLevel.BRONZE);
        }
    }

    public List<Member> getAllMembers(){
        return memberRepository.getAll(Member.class);
    }
}
