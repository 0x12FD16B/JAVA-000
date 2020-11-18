package io.x16fd16b.assignment02.beanware.auto.decorator;

import io.x16fd16b.assignment02.beanware.auto.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FooService Decorator
 *
 * @author David Liu
 */
@Service
public class FooServiceDecorator implements FooService {
    @Autowired
    private FooService fooService;

    @Override
    public String foo() {
        System.out.println("FooServiceDecorator: decorate foo");
        return fooService.foo();
    }
}
