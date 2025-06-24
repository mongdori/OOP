package hello.core.singleton;

import hello.core.StatefulService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void stateful() {

        //Bean은 싱글톤으로 유지되기 때문에 여러 클라이언트가 해당 Bean 객체를 생성해도 결국 같은 객체를 공유한다.
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
        int user1 = statefulService1.order("User1", 10000);
        int user2 = statefulService2.order("User2", 20000);

        // 공유되는 객체의 필드를 다시 사용하여 order를 20000으로 했으므로 user1Price도 20000이 나온다.
//        int user1Price = statefulService1.getPrice(); // expecting 10000, but 20000
//        int user2Price = statefulService2.getPrice();

        System.out.println("user1Price = " + user1); // expecting 10000, but 20000
        Assertions.assertThat(user1).isEqualTo(user2);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}