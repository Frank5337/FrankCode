package com.zbl.singleton;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 20:41 2019/12/16
 * @Description:
 * @Version: $
 * 懒汉式, lazy loading
 * 虽然达到了按需初始化的目的, 但却带来线程不安全的问题
 * 多线程访问的时候, 会有问题
 * 可以通过synchronized来解决, 却也带来了效率下降
 * 在if (INSTANCE == null) { 之后锁无法实现单例
 * so 推出双重检测
 * 曾经认为是最完美的
 */
public class Mgr06 {

    /**
     * java 汇编有一个优化 会指令重排
     */
    private static volatile Mgr06 INSTANCE;//JIT  不加volatile没问题是因为没有进行JIT的优化

    private Mgr06() {
    }

    /**
     * static synchronized 锁定的是类 在当前 锁定的是Mgr06.class
     * @return
     */
    public static Mgr06 getInstance() {
        if (INSTANCE == null) {//有必要判断,
            //妄图通过减少同步代码块的方式提高效率,  然后不行
            synchronized (Mgr06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }

        }
        return INSTANCE;
    }

    //同一个类的不同对象, hash码是不同的
    //此种方法没问题
    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i -> new Thread(() -> {
            System.out.println(getInstance().hashCode());
        }).start());
    }
}
