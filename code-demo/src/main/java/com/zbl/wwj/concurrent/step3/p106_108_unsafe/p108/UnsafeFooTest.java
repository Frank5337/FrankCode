package com.zbl.wwj.concurrent.step3.p106_108_unsafe.p108;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/9
 * @Description:
 * @Version: $
 */
public class UnsafeFooTest {

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        Simple simple = new Simple();
//        System.out.println(simple.getL());
        //会初始化
//        Simple simple = Simple.class.newInstance();
        //不会初始化
//        Class<?> aClass = Class.forName("com.zbl.wwj.concurrent.step3.p106_108_unsafe.p108.UnsafeFooTest.Simple");

        //unsafe绕过初始化 之间开辟内存
        Unsafe unsafe = getUnsafe();
//        Simple o = (Simple)unsafe.allocateInstance(Simple.class);
//        System.out.println(o.getL());
//        System.out.println(o.getClass());
//        System.out.println(o.getClass().getClassLoader());

//        Guard guard = new Guard();
//        guard.work();
//
//        System.out.println("===========");
//        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
//        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
//        guard.work();

//        byte[] bytes = loadClassContent();
//        Class cLass = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
//        Object o = cLass.newInstance();
//        System.out.println(o.getClass().getClassLoader());
//        int v = (int) cLass.getMethod("get").invoke(o);
//        System.out.println(v);

        System.out.println(sizeOf(new Simple()));

        Map map = new HashMap<>();
        map.put("aaa","aa");
        map.remove("aaa");
        System.out.println(map.get("aaa"));

    }

    private static long sizeOf(Object obj) {
        Unsafe unsafe = getUnsafe();
        Set<Field> fields = new HashSet<Field>();
        Class c = obj.getClass();
        while (c != Object.class) {
            Field[] declaredFields = c.getDeclaredFields();
            for (Field f : declaredFields) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        long maxOffSet = 0;
        for (Field field : fields) {
            long offSet = unsafe.objectFieldOffset(field);
            if (offSet > maxOffSet) {
                maxOffSet = offSet;
            }
        }

        return ((maxOffSet / 8) + 1) * 8;
    }

    private static byte[] loadClassContent() throws Exception {
        File f = new File("D:\\develop\\A.class");
        FileInputStream fis = new FileInputStream(f);
        byte[] content = new byte[(int) f.length()];
        fis.read(content);
        fis.close();
        return content;
    }

    static class Guard {
        private int ACCESS_ALLOWED = 1;

        private boolean allow() {
            return 42 == ACCESS_ALLOWED;
        }

        public void work() {
            if (allow()) {
                System.out.println("I am working by allow");
            }
        }
    }

    static class Simple {
        private long ll = 0;
        private int l = 10;
        private byte b = (byte) 0x01;
        public Simple() {
            this.l = 1;
            System.out.println("=============");
        }

        public long getL() {
            return l;
        }
    }
}
