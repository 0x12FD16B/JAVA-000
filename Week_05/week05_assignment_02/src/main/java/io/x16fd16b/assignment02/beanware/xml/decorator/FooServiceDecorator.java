package io.x16fd16b.assignment02.beanware.xml.decorator;

import io.x16fd16b.assignment02.beanware.xml.FooService;

/**
 * FooService Decorator
 *
 * @author David Liu
 */
public class FooServiceDecorator implements FooService {
    private final FooService fooService;

    public FooServiceDecorator(FooService fooService) {
        this.fooService = fooService;
    }

    @Override
    public String foo() {
        System.out.println("FooServiceDecorator: decorate foo");
        return fooService.foo();
    }
}
