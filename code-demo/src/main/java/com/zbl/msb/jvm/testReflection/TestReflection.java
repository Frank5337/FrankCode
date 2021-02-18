package com.zbl.msb.jvm.testReflection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;

/**
 * @Author: zbl
 * @Date: Created in 13:53 2020/2/9
 * @Description:
 * @Version: $
 */
public class TestReflection {

    public static void main(String[] args) throws Exception {
        BufferedReader fr = new BufferedReader(new FileReader("D:\\work\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\testReflection\\test.properties"));
        String str = "";
        while ((str = fr.readLine()) != null) {
            str = str.split("=")[1];
            System.out.println(str);
            //把名字叫str的类装载到内存
            Class<?> aClass = Class.forName(str);
            //new 一个aClass 对象
            Object o = aClass.newInstance();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("mm")) {
                    method.invoke(o);
                }
                if (method.getName().equals("m1")) {
                    //可变参数
                    method.invoke(o, 1, 2);
                }
                if (method.getName().equals("getS")) {
                    Class<?> returnType = method.getReturnType();
                    System.out.println(returnType.getName());
                }
            }
        }

    }

}

class T {
    int i;
    String s;

    static {
        System.out.println("装载了 T");
    }

    public T() {
        System.out.println("T constructed !");
    }

    public void mm(){
        System.out.println(" mmm invoke");
    }

    public void m1(int i, int j) {
        this.i = i + j;
        System.out.println(this.i);
    }

    public String getS () {
        return s;
    }


}
