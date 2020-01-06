package com.zbl.strategy;

/**
 * @Author: zbl
 * @Date: Created in 15:09 2019/12/25
 * @Description: 比较器, 其实就是写比较策略的
 * @Version: $
 */
public interface Comparator<T> {
    int compare (T o1, T o2);
}
