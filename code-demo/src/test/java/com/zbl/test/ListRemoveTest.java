package com.zbl.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Create in 2020/10/1
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class ListRemoveTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        //java.util.ConcurrentModificationException
//        for (Object o : list) {
//            list.remove(o);
//        }

        //OK的
//        for (int i = 0; i < list.size() ; i++) {
//            list.remove(list.get(i));
//        }

        //
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();//没有调用此方法会报错java.lang.IllegalStateException
            iterator.remove();
        }
    }
}
