package com.zbl.msb.designpatterns.strategy;

/**
 * @Author: zbl
 * @Date: Created in 15:04 2019/12/17
 * @Description:
 * @Version: $
 */
public class Sorter {

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length -1 ; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr, i, minPos);

        }
    }

    /**
     * i 和 j 的值互换
     * @param arr
     * @param i
     * @param j
     */
    static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
