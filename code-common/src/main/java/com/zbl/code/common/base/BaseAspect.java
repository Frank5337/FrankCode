package com.zbl.code.common.base;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * @Author: zbl
 * @Date: Created in 10:21 2019/8/21
 * @Description: 通用切面
 * @Version: $
 */
@Slf4j
public class BaseAspect {
    @Resource
    protected HttpServletRequest req;

    /**
     * 方法上或者类上是否加了某个注解
     */
    protected boolean require(JoinPoint joinPoint, Class<? extends Annotation> annotationClass) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().isAnnotationPresent(annotationClass)
                || methodSignature.getMethod().getDeclaringClass().isAnnotationPresent(annotationClass);
    }

    /**
     * 获取控制器名称
     *  joinPoint.getSignature() 获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
     */
    protected String getCtrl(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringType().getSimpleName();
    }

    /**
     * 获取方法名称
     */
    protected  String getMethod(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    protected Long getAdminId() {
        return (Long) req.getSession().getAttribute(Global.KEY_ADMIN_ID);
    }

    protected Long getUserId() {
        return (Long) req.getSession().getAttribute(Global.KEY_USER_ID);
    }

    protected Long getAdminRoleId() {
        return (Long) req.getSession().getAttribute(Global.KEY_ADMIN_ROLE_ID);
    }

}
