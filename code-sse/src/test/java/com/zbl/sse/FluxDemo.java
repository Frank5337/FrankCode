package com.zbl.sse;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: zbl
 * @Date: 21:50 2020/7/10
 * @Description:
 */
public class FluxDemo {

    public static void main(String[] args) {
        String[] s = new String[] {"xx", "oo"};
        //发布者(被观察)
        Flux<String> flux1 = Flux.just(s);
        //sout 订阅
        flux1.subscribe(System.out::println);

        System.out.println("------");
        List<String> list = Arrays.asList("hello", "flux");
        Flux<String> flux2 = Flux.fromIterable(list);
        //sout 订阅
        flux2.subscribe(System.out::println);

        System.out.println("------");
        Stream<String> stream = Stream.of("hello", "flux");
        Flux<String> flux3 = Flux.fromStream(stream);
        //sout 订阅
        flux3.subscribe(System.out::println);


    }
}
