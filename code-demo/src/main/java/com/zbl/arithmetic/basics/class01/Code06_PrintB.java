package com.zbl.arithmetic.basics.class01;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/9
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Code06_PrintB {

    /**
     * & 都是1才是1
     * @param num
     */
    private static void print(int num) {
        for (int i = 31; i >=0 ; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //32位
        int num = 83928328;
        int a = 123444;
        print(num);
        print(a);
        System.out.println("===========");
        print(num & a);//都是1     则1
        print(num | a);//有1      则1
        print(num ^ a);//相同为0 不同为1
        System.out.println("===========");
        int aa = 1024;
        print(aa);
        print(aa >> 1);
        print(aa >>> 1);

        System.out.println("===========");
        int c = 5;
        int d = -5;
        int e = ~c + 1;
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        int min = Integer.MIN_VALUE;
        System.out.println(min);
        System.out.println(~min + 1);

        //取反+1 = 加负号
        int z = 5;
        int zz = ~z +1;
        int zzz = ~zz +1;
        System.out.println(z);
        System.out.println(zz);
        System.out.println(zzz);

    }

    @Test
    public void test01() throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(new File("D:\\work\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\arithmetic\\class01\\Code03_InsertionSort.java"))){
            System.out.println(fileInputStream.read());
        } catch (IOException e) {
            throw e;
        }
    }




}
