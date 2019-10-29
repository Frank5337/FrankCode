package com.zbl.code.common.util;

import java.text.DecimalFormat;

/**
 * @Author: zbl
 * @Date: Created in 10:58 2019/8/26
 * @Description:
 * @Version: $
 */
public class NumUtil {
    /**
     * 二进制转十进制
     *
     * @param binaryNumber 二进制数字
     * @return 十进制
     */
    public static int binaryToDecimal(int binaryNumber) {

        int decimal = 0;
        int p = 0;
        while (true) {
            if (binaryNumber == 0) {
                break;
            } else {
                int temp = binaryNumber % 10;
                //math.pow 返回第一个参数的第二个参数次方。
                decimal += temp * Math.pow(2, p);
                binaryNumber = binaryNumber / 10;
                p++;
            }
        }
        return decimal;
    }

    /**
     * 位数不够补0
     *
     * @param zeroNum 几位数 如 5位数 不够补0, "00000"
     * @param num     要补0的数字
     * @return 补0结果
     */
    public static String repairZero(String zeroNum, String num) {
        return new DecimalFormat(zeroNum).format(Integer.parseInt(num));
    }

    public static String repairZero(String zeroNum, Integer num) {
        return new DecimalFormat(zeroNum).format(num);
    }

    public static void main(String[] args) {
        System.out.println(binaryToDecimal(Integer.parseInt(new String("010101"))));
        int binaryNum = binaryToDecimal(Integer.parseInt(new String("10101")));
        System.out.println(binaryNum);
        System.out.println(Integer.toBinaryString(21));
        System.out.println(new DecimalFormat("000000").format(Integer.parseInt("10101")));
    }


}
