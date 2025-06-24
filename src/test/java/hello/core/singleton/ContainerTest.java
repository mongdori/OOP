package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerTest {

    AppConfig appConfig = new AppConfig();

    @Test
    @DisplayName("같은 객체를 생성하면 참조가 다르다")
    void pureContainer() {

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService2 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 객체를 호출한다.")
    void singletonTest() {

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너는 기본적으로 싱글톤 패턴을 사용한다.ㅓ")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2); // isSameAs()는 참조를 비교.
    }

    @Test
    @DisplayName("AppConfig 내의 순수 Java 코드로 new를 하면 객체 생성이 new한만큼 된다.")
    void setAppConfig() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);// 구체 클래스를 사용하는 이유는 구체 클래스의 getAddress() 메서드를 사용하기 위해서
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);// 구체 클래스를 사용하는 이유는 구체 클래스의 getAddress() 메서드를 사용하기 위해서

        System.out.println("memberService -> MemoryMemberRepository = " + memberService.getAddress());
        System.out.println("orderService -> MemoryMemberRepository = " + orderService.getAddress());

        Assertions.assertThat(memberService.getAddress()).isSameAs(orderService.getAddress());
    }
}
