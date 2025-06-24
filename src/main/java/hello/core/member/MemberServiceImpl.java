package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // MemberRepository 인터페이스르 잘 의존하고 있다. 하지만 오른쪽에 MemberRepository의 구현체인 MemoryMemberRepository까지 의존하고 있는 것이 문제점. DIP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //test용
    public MemberRepository getAddress() {
        return memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해서 memberRepository.save()는 MemoryMemberRepository의 save()가 호출된다.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); // 마찬가지
    }
}
