package io.x12fd16b.week7.sat.assignment02.support.annotations;

import io.x12fd16b.week7.sat.assignment02.support.enums.DynamicDataSourceEnum;

import java.lang.annotation.*;

/**
 * 数据源选择器注解
 *
 * @author David Liu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {

    DynamicDataSourceEnum value() default DynamicDataSourceEnum.MASTER;

    boolean clear() default true;
}
