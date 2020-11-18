package io.x16fd16b.assignment02.beanware.xml;

import io.x16fd16b.assignment02.beanware.xml.decorator.FooServiceDecorator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanWareConfigurationTest
 *
 * @author David Liu
 */
public class BeanWareConfigurationTest {

    @Test
    public void testBeanWare() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        Assert.assertEquals("foo", ctx.getBean(FooServiceDecorator.class).foo());
    }
}
