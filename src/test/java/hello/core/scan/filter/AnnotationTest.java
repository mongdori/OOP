package hello.core.scan.filter;

import hello.core.MyExcludeComponent;
import hello.core.MyIncludeComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);

    @Test
    void includeAnnotation() {

        BeanB beanB = ac.getBean("beanB", BeanB.class);
        Assertions.assertThat(beanB).isNotNull();
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanA", BeanA.class));

    }

    @Configuration
    @ComponentScan(
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            },
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
            }
    )
     static class AnnotationConfig {


    }
}
