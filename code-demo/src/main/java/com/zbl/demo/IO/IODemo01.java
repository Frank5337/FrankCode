package com.zbl.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Created in 14:55 2020/3/17
 * @Description:
 * @Version: $
 */
public class IODemo01 {

    public static void main(String[] args) {
        int b = 0;
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File("D:\\work\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\IO\\text.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
            System.exit(-1);
        }

        try {
            long num = 0;
            while ((b = in.read()) != -1) {
                System.out.println((char)b);
                num++;
            }
            in.close();
            System.out.println("共读取了 : " + num + "个字节");
        }  catch (IOException e ) {
            System.out.println("文件读取错误");
            System.exit(-1);
        }
    }
}
