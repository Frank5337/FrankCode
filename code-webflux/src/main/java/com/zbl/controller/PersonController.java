package com.zbl.controller;

import com.zbl.pojo.Person;
import com.zbl.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: 19:14 2020/7/18
 * @Description:
 */
// 注解式
// 函数式
@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping
    public Mono<Object> get() {
        System.out.println("线程get " + Thread.currentThread().getName());
        System.out.println("--1--");
        Mono<Object> mono = Mono.create(sink -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //组装数据序列
            sink.success(personService.getPerson());
        })
                .doOnSubscribe(sub -> {
                    //先执行这里 订阅数据
                    System.out.println("sub");
                    System.out.println("线程get " + Thread.currentThread().getName());
                })
                .doOnNext(data -> {
                    //得到数据
                    System.out.println("data: " + data);
                    System.out.println("线程get " + Thread.currentThread().getName());
                })
                .doOnSuccess(onSuccess -> {
                    //整体完成
                    System.out.println("onSuccess");
                    System.out.println("线程get " + Thread.currentThread().getName());
                });

        System.out.println("--2--");
        //SpringMVC 值 在这个环节准备好
        //得到一个包装数据序列 -> 包含特征 -> 容器拿到这个序列 -> 执行序列里的方法

        //Ajax a() -> b()    ->b (c()) ->
        // 1, 写回调方法, 让b调用
        // 2, 直接传方法过去

        //看起来像是异步, 实质上, 阻塞的过程在容器内部
        return mono;
    }

    @GetMapping("/ps")
    public Mono<Person> getPs() {
        return personService.getPersons().next();

    }

    @GetMapping("/xxoo")
    // ServerHttpRequest webFlux 中特有的
    // 拓展思维 SpringCloud Gateway
    public Mono<Object> get(String name,
                            WebSession session,
                            ServerHttpRequest request
    ) {
        if (StringUtils.isEmpty(session.getAttribute("qwe"))) {
            session.getAttributes().put("qwe", "啊");
        }
        System.out.println("qwe = " + session.getAttribute("qwe"));
        System.out.println("request: " + request);
        System.out.println(request.getHeaders());
        //http://localhost:8080/person/xxoo?name=jiangyuwei
        System.out.println(request.getQueryParams().get("name"));


        //just 静态初始化
        return Mono.just("么么哒: " + name);
    }

    //服务器推消息
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> sse() {
        //next 弹出一个 doOnNext 等有数据了 doSomething
        //封装对象
        return Flux.fromStream(IntStream.range(1, 100).mapToObj(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 对象, 连带里面的方法给了容器
            return i;
        }))
                .doOnSubscribe(sub -> {
                    //开始时, 订阅,,
                    System.out.println("sub");
                })
                .doOnComplete(() -> {
                    //完成时
                    System.out.println("doOnComplete");
                })
                .doOnNext(data -> {
                    // 0-多  运行时
                    System.out.println("有data了: " + data);
                })

                ;
    }


}
