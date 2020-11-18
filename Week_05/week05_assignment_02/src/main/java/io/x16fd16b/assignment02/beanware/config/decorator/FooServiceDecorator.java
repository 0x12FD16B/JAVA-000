package io.x16fd16b.assignment02.beanware.config.decorator;

import io.x16fd16b.assignment02.beanware.config.FooService;
import org.springframework.stereotype.Service;

/**
 * FooService Decorator
 *
 * @author David Liu
 */
@Service
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
