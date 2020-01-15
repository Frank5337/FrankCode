package com.zbl.demo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zbl
 * @Date: Created in 20:27 2020/1/14
 * @Description:
 * @Version: $
 */
public class FileDemo {
    /**
     * File 的equals -> File的compareTo -> 实际比较的文件路径(不同系统下不同) -> String的比较(不区分大小写)
     * @param args
     */
    public static void main(String[] args) {
        Map<File, String> map = new HashMap<>();
        File file1 = new File("C:\\Users\\ThinkPad\\Desktop\\授权文件 (1).txt");
        File file2 = new File("C:\\Users\\ThinkPad\\Desktop\\授权文件 (2).txt");
        File file3 = new File("C:\\Users\\ThinkPad\\Desktop\\授权文件 (3).txt");
        File file4 = new File("C:\\Users\\ThinkPad\\Desktop\\授权文件 (1).txt");
        File file5 = new File("C:\\Users\\ThinkPad\\Desktop\\授权文件 (1).txt");
        map.put(file1, "1");
        map.put(file2, "2");
        map.put(file3, "3");
        map.put(file4, "4");
        map.put(file5, "5");
        System.out.println(map);
        System.out.println("file1: " + file1.hashCode());
        System.out.println("file2: " + file2.hashCode());
        System.out.println("file3: " + file3.hashCode());
        System.out.println("file4: " + file4.hashCode());
        System.out.println("file5: " + file5.hashCode());
    }

}
