package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 만약 @Configuration을 빼면 @Bean으로 작성한 반환 객체들은 스프링 컨테이너에 등록된다. 하지만 CGLIB를 사용하지 않을 것이고, 순수 내가 만든 클래스가 등록된다.
 * 그렇게 되면 new 코드들은 싱글톤이 깨질 것이다.
 * @Configuration이 있으면 검증을 한번 한다. 해당 객체가 스프링 컨테이너에 등록되어 있는지 없는지.
 * 있으면 컨테이너에 등록된 스프링 빈을 반환하여 공유되도록 보장할 것이고, 컨테이너에 없다면 해당 객체를 스프링 빈으로 생성한다.
 */
public class AppConfig {

    // 애플리케이션에서 해당 역할에 사용되는 구현체를 지정함으로 역할과 구현을 분리함을 직관적으로 볼 수 있게 한다.

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }
}
