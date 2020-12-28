package com.zbl.test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/23
 * @Description:
 * @Version: $
 */
public class AliBole {

    public static void main(String[] args) {
        List<Double> list = Arrays.asList(1.0, 1.5, 2.7, 2.3);
        getMaxEarning(list);
    }

    public static void getMaxEarning(List<Double> list) {
        double[] arr = new double[2];
        double max = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j) - list.get(i) > max) {
                    max = list.get(j) - list.get(i);
                    arr[0] = list.get(i);
                    arr[1] = list.get(j);
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(((arr[1] - arr[0]) / arr[0]) * 100 + "%");
    }

    public static double[] getMaxEarning2(List<Double> list) {
        double[] arr = new double[2];
        double max = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j) - list.get(i) > max) {
                    max = list.get(j) - list.get(i);
                    arr[0] = list.get(i);
                    arr[1] = list.get(j);
                }
            }
        }
        return arr;

//        System.out.println(Arrays.toString(arr));
//        System.out.println(((arr[1] - arr[0]) / arr[0]) * 100 + "%");
    }


}
