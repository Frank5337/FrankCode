package com.mashibing.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * @Author: zbl
 * @Date: 22:06 2020/7/10
 * @Description:
 */
@RestController
public class MonoController {

    @GetMapping("/1")
    public String test() {
        return getResult();
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        System.out.println("---1");
        Mono<String> result = Mono.create(sink -> {
            getResult();
        });
        System.out.println("---2");
        return result;
    }

    private String getResult() {
        try {
            Thread.sleep(2000);
            System.out.println("getResult");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "xxoo";
    }
}
