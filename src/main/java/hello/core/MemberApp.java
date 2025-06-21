package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        /**
         * 여기서는 MemberApp이 클라이언트 (요청자)가 되고, MemberService가 서버가 된다.
         * 그리고 MemberService는 서버이자 다시 클라이언트로서 MemberRepository에게 요청한다.
         */
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /**
         * Spring Container (ApplicationContext)에 해당 역할에 구현 객체를 생성하고 연결하는 메서드를 Bean으로 등록하여 이제부터 Spring Container에서 필요한 협력 역할과 구현체를 Bean으로 가져와 사용한다.
         */
        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = h.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "first", Grade.VIP);
        memberService.join(member);
        System.out.println("new member = " + member.getName());
        Member findMember = memberService.findMember(member.getId());
        System.out.println("find Member = " + findMember.getName());
    }
}
