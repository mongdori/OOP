package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        /**
         * 여기서는 MemberApp이 클라이언트 (요청자)가 되고, MemberService가 서버가 된다.
         * 그리고 MemberService는 서버이자 다시 클라이언트로서 MemberRepository에게 요청한다.
         */
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "first", Grade.VIP);
        memberService.join(member);
        System.out.println("new member = " + member.getName());
        Member findMember = memberService.findMember(member.getId());
        System.out.println("find Member = " + findMember.getName());
    }
}
