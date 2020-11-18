package io.x16fd16b.assignment02.beanware.auto;

import org.springframework.stereotype.Service;

/**
 * FooService
 *
 * @author David Liu
 */
@Service
public class FooServiceImpl implements FooService {

    @Override
    public String foo() {
        return "foo";
    }
}
