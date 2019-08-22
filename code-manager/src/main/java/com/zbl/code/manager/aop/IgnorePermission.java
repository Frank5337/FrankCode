package com.zbl.code.manager.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zbl
 * @Date: Created in 9:28 2019/8/22
 * @Description: 忽略权限判断
 * @Version: $
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnorePermission {
}
