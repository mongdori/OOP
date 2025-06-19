package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member) ;
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // 사용하는 HashMap은 Member 타입을 저장하고 있다. 그래서 get을 하면 Member 타입을 가지고 와서 반환한다.
    }
}
