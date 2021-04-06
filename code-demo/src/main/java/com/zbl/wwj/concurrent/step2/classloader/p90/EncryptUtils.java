package com.zbl.wwj.concurrent.step2.classloader.p90;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public final class EncryptUtils {

    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtils() {
        //empty
    }

    public static void doEncrypt(String source, String target) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ ENCRYPT_FACTOR);
            }
            fos.flush();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
//        doEncrypt("D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\aaa.txt"
//        ,"D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\bbb.txt");
//
//        doEncrypt("D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\bbb.txt"
//                ,"D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\ccc.txt");
        doEncrypt("D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\classloader3\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\MyObject.class"
                ,"D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\classloader3\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\MyObject1.class");
    }
}
