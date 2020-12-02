package io.x12fd16b.week7.sat.assignment02.support;

import io.x12fd16b.week7.sat.assignment02.support.annotations.DataSourceSelector;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源上下文切面
 *
 * @author David Liu
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class DataSourceContextSelectAspect {

    @Around("@annotation(io.x12fd16b.week7.sat.assignment02.support.annotations.DataSourceSelector)")
    public Object setDynamicDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean clear = true;
        try {
            Method method = this.getMethod(joinPoint);
            DataSourceSelector dataSourceImport = method.getAnnotation(DataSourceSelector.class);
            clear = dataSourceImport.clear();
            DataSourceContextHolder.set(dataSourceImport.value().getDsName());
            log.info("========数据源切换至：{}", dataSourceImport.value().getDsName());
            return joinPoint.proceed();
        } finally {
            if (clear) {
                DataSourceContextHolder.clear();
            }

        }
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
