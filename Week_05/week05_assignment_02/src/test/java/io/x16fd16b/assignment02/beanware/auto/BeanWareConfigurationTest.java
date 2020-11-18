package io.x16fd16b.assignment02.beanware.auto;

import io.x16fd16b.assignment02.beanware.auto.decorator.FooServiceDecorator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanWareConfigurationTest {

    @Test
    public void testBeanWareConfigurationTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanWareConfiguration.class);
        Assert.assertEquals("foo", ctx.getBean(FooServiceDecorator.class).foo());
    }
}