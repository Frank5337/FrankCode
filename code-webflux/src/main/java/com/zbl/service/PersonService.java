package com.zbl.service;

import com.zbl.pojo.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zbl
 * @Date: 19:18 2020/7/18
 * @Description:
 */
@Service
public class PersonService {

    static ConcurrentHashMap<Integer, Person> map = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i <100 ; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("jiangyuwei " + i);
            map.put(i, person);
        }
    }

    public Person getPerson() {
        System.out.println("线程getPerson " + Thread.currentThread().getName());
        return map.get(1);
    }

    public Flux<Person> getPersons() {
        // 直接给
        // 响应式数据源
        return Flux.fromIterable(map.values());

    }


}
