package com.zbl.code.client.aop;

import com.zbl.code.common.base.BaseAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by hook on 2018/5/17.
 * <p>
 * 后台管理切面，包括了权限判断和日志记录
 */
@Aspect
@Component
public class ClientAspect extends BaseAspect {

    @Pointcut("execution(public * com.zbl.code.client..*.*(..))")
    private void clientPointCut() {

    }

    /**
     * 日志记录
     */
    @AfterReturning(pointcut = "clientPointCut()", returning = "rtnVal")
    public void afterReturning(JoinPoint joinPoint, Object rtnVal) {

    }
}