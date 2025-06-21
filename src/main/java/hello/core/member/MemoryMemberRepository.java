package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // static이기 때문에 외부 구성 클래스에서 다른 참조값으로 생성을 하여도 공용으로 사용하기에 같은 인스턴스를 사용하게 된다.
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
