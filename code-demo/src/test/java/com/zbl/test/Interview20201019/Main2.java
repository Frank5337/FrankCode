package com.zbl.test.Interview20201019;

/**
 * @Author: zbl
 * @Date: Create in 2020/10/19
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Main2 {

    public static void stringReplace(String text) {
        text = text.replace('j', 'c');
    }

    public static void bufferReplace(StringBuffer text) {
        text = text.append("c");
    }

    public static void main(String[] args) {
        String textString = new String("java");
        StringBuffer textBuffer = new StringBuffer("java");
        stringReplace(textString);
        bufferReplace(textBuffer);
        System.out.println(textString + textBuffer);
    }
}
