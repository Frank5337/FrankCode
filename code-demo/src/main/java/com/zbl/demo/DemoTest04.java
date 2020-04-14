package com.zbl.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 14:31 2020/2/26
 * @Description:
 * @Version: $
 */
public class DemoTest04 {

    @Test
    public void test01() throws Exception{
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        Integer temp;
        temp = list.get(0);
        list.set(0, list.get(2));
        list.set(2, temp);
        System.out.println(list);

    }

    @Test
    public void test02() throws Exception {
        int x;
        if ((x = 33) > 55) {
            System.out.println(1);
        }
        System.out.println(x);
    }

    @Test
    public void test03() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("zbl", "test");
        Set set = hashMap.keySet();
//        for (Object o : set) {
//            System.out.println(o);
//        }
    }

    @Test
    public void test04() throws Exception {
        if (false || false) {
            System.out.println(1);
        }
    }

}
