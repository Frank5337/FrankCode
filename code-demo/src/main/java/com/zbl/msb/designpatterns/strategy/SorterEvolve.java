package com.zbl.msb.designpatterns.strategy;

/**
 * @Author: zbl
 * @Date: Created in 15:04 2019/12/17
 * @Description:
 * @Version: $
 */
public class SorterEvolve<T> {

    /**
     * 你得告诉我, T类型 怎么比较
     * @param arr
     * @param comparator
     */
    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length -1 ; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j], arr[minPos]) == -1 ? j : minPos;
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
     void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
