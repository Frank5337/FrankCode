package com.zbl.leetcode.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/25 17:09
 * @Description:
 * @Version: $
 */
public class Test957 {

    public static void main(String[] args) {
//        int[] cells = new int[]{0,1,0,1,1,0,0,1};
        int[] cells = new int[]{1, 1, 0, 1, 1, 0, 1, 1};
        int n = 6;
        System.out.println(Arrays.toString(cells));
        System.out.println(Arrays.toString(new Test957().prisonAfterNDays(cells, n)));
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] temp = new int[cells.length];
        Map m = new HashMap<>();
        Set s = new HashSet();
        for (int n = 0; n < N; n++) {
            for (int i = 0; i < cells.length; i++) {
                if (i - 1 >= 0 && i + 1 < cells.length) {
                    int q = cells[i - 1];
                    int h = cells[i + 1];
                    if (q == h) {
                        temp[i] = 1;
                    } else {
                        temp[i] = 0;
                    }
                }
                if (i == 0 || i == cells.length - 1) {
                    temp[i] = 0;
                }

            }
            cells = temp;
            temp = new int[cells.length];
            if (s.contains(Arrays.toString(cells))) {
                break;
            }
            m.put(n, cells);
            s.add(Arrays.toString(cells));
        }
        System.out.println(s.size());
        return (int[]) m.get((N % s.size() == 0 ? N : N % s.size()) - 1);
    }
}
