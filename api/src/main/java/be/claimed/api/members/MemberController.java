package be.claimed.api.members;

import be.claimed.domain.members.Member;
import be.claimed.service.members.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    private MemberService memberService;
    private MemberMapper memberMapper;

    @Inject
    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MemberDto registerMember(@RequestBody MemberDto memberToRegister) {
        Member debugMember = memberService.createMember(memberMapper.toDomain(memberToRegister));
        return memberMapper.toDto(debugMember);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembers().stream()
                .map(member -> memberMapper.toDto(member))
                .collect(Collectors.toList());
    }
}
