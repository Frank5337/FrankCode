package com.zbl.aop;

import com.zbl.dds.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 2020/11/12
 * @Description:  读写分离切面
 * @Version: $
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    @Pointcut("@annotation(com.zbl.aop.Slave) " +
            "|| (execution(* com.zbl.mapper..*.find*(..)) " +
            "|| execution(* com.zbl.mapper..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.zbl.aop.Master2)")
    public void writePointcut2() {

    }

    @Pointcut("@annotation(com.zbl.aop.Master) " +
            "|| execution(* com.zbl.mapper..*.insert*(..)) " +
            "|| execution(* com.zbl.mapper..*.add*(..)) " +
            //"|| execution(* com.zbl.mapper..*.update*(..)) " +
            "|| execution(* com.zbl.mapper..*.edit*(..)) " +
            "|| execution(* com.zbl.mapper..*.delete*(..)) " +
            "|| execution(* com.zbl.mapper..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DynamicDataSourceHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DynamicDataSourceHolder.master();
    }

    @Before("writePointcut2()")
    public void write2() {
        DynamicDataSourceHolder.master2();
    }

    /**
     * 清理, 防止串连接 or 内存泄露问题
     */
    @After("writePointcut() || readPointcut()")
    public void after() {
        log.info("清理连接");
        DynamicDataSourceHolder.clearDbType();
    }

    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     * 读从库的可以写配置文件中指定读取
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}