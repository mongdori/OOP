package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFindTest {

    //AppConfig에서 등록된 애플리케이션 빈을 찾기 위해 스프링 컨테이너 접근
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findAllBean() {

        // 스프링 컨테이너에서 등록된 빈 이름들을 배열로 가지고 온다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 배열을 풀기 위해 반복문으로 푼다. getBean() 메서드를 실행하기 위해선 인자로 빈의 이름이 필요하다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = " + bean);
        }
    }

    @Test
    void findBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 배열로 풀어낸 빈 이름들을 가지고 있는 상태. 빈 이름을 가지고 빈의 정의(속성들)를 알 수 있는 메서드 .getBeanDefinition()
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 내부적으로 스프링을 확장하기 위해 등록한 것이 아닌 Application에 사용자가 등록한 빈들 : ROLE_APPLICATION
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition = " + beanDefinitionName);
            }
        }
    }
}
