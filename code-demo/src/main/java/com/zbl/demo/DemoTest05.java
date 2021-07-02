package com.zbl.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/18 16:56
 * @Description:
 * @Version: $
 */
public class DemoTest05 {

    private final List<Integer> syncPostHandlers = new ArrayList<>();

    public static void main(String[] args) {
        DemoTest05 demoTest05 = new DemoTest05();
        List<Integer> syncPreHandlers = Arrays.asList(1);
        Optional.ofNullable(syncPreHandlers).ifPresent(handlers -> syncPreHandlers.addAll(handlers));
        System.out.println(demoTest05.syncPostHandlers);
    }
}
