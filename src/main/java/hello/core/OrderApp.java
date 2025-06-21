package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        OrderService orderService = appConfig.orderService();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = h.getBean("memberService", MemberService.class);
        OrderService orderService = h.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "first", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "one", 30000);
        System.out.println("order = " + order.toString());
        System.out.println("order.cal = " + order.calculatePrice(order.getItemPrice(), order.getDiscountPrice()));
    }
}
