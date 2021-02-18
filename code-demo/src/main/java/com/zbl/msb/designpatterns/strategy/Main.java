package com.zbl.msb.designpatterns.strategy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 15:02 2019/12/17
 * @Description: 策略模式
 * @Version: $
 */
public class Main {

    private static Cat[] cats = new Cat[]{new Cat(2,2) ,
            new Cat (1,1),
            new Cat (3,3),
    };

    public static void main(String[] args) {

        System.out.println("排序前" + Arrays.toString(cats));
        Sorter.sort(cats);
        System.out.println("排序后" + Arrays.toString(cats));

    }

    @Test
    public void test01() throws Exception{

        System.out.println("排序前" + Arrays.toString(cats));
        new SorterEvolve<Cat>().sort( cats, (o1, o2) -> {
            if (o1.weight > o2.weight) return 1;
            if (o1.weight < o2.weight) return -1;
            return 0;
        });
        System.out.println("排序后" + Arrays.toString(cats));
    }


}
