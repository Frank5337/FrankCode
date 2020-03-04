package com.zbl.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

}
