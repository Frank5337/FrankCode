package com.zbl.wwj.concurrent.step2.p58;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/21
 * @Description:
 * @Version: $
 * Immutable 不可变
 */
public class ImmutableClient {

    public static void main(String[] args) {
        //Share data
        Person person = new Person("Alex", "California");

        IntStream.range(0, 5).forEach( i -> new UsePersonThread(person).start());
    }
}
