package com.nmgwr.admin.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class PageQueryCheck {

    @Around("execution(void com.nmgwr.admin.common.utils.PageQuery.init(Object))")
    public Object pageQueryParamsCheck(final ProceedingJoinPoint pjp)throws Throwable{
        try {
            Object args[] = pjp.getArgs();
            System.out.println("参数为：" + Arrays.asList(args));
            Object o = pjp.proceed();
            return o;
        }catch (Throwable e){
            throw e;
        }
    }

}
