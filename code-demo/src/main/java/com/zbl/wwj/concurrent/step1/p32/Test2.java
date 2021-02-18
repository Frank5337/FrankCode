package com.zbl.wwj.concurrent.step1.p32;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/10
 * @Description:
 * @Version: $
 */
public class Test2 {

    public void test() {
        //获取堆栈追踪信息
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Arrays.asList(stackTrace).stream()
                .filter(e -> !e.isNativeMethod())
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber())
                .ifPresent(System.out::println));
    }
}
