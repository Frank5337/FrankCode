package com.zbl.demo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zbl
 * @Date: 22:38 2020/4/14
 * @Description:
 */
public class ChuangLinTest01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
        int budget = Integer.parseInt(enter.replace(" ", ""));
        String priceStr = sc.nextLine();
        String[] priceArr = priceStr.split(" ");
        int[] products = new int[priceArr.length];
        for (int i = 0; i <priceArr.length ; i++) {
            products[i] = Integer.parseInt(priceArr[i]);
        }
        int temp;
        for (int i = 0; i < products.length; i++) {
            for (int j = i; j <products.length; j++) {
                if (products[i] > products[j]) {
                    temp = products[i];
                    products[i] = products[j];
                    products[j] = temp;
                }
            }
        }
        int result = 0;
        for (int product : products) {
            result += product;
            if (result >= budget) {
                result -= product;
                break;
            }
        }
        System.out.println(result);



    }

}
