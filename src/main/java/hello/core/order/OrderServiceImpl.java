package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    결국 클라이언트 코드를 변경함으로 영향을 주기에 OCP 위반.
    //    OCP는 변경하지 않고 확장할 수 있어야 하는 원칙이지만, 현재 클라이언트 코드를 수정함으로 OCP를 위반하게 되는 것.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 외부 구성 클래스에서 객체의 생성과 연결 책임을 맡아 해당 객체의 생성 시 (생성자 실행 시) 핋요한 협력 관계의 구현체를 함께 주입해준다.
     * (필요한 협력 관계의 추상화들은 해당 구현체에서 필드로 알고 있어야 한다. (의존하고 있어야 한다.)
     */
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    //test용
    public MemberRepository getAddress() {
        return memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
