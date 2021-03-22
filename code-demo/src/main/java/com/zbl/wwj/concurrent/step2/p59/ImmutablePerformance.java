package com.zbl.wwj.concurrent.step2.p59;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/22
 * @Description:
 * @Version: $
 */
public class ImmutablePerformance {

    public static void main(String[] args) throws Exception {
        long startTimeStamp = System.currentTimeMillis();

        Thread t1 = new Thread(ImmutablePerformance::test);
        Thread t2 = new Thread(ImmutablePerformance::test);
        Thread t3 = new Thread(ImmutablePerformance::test);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        long endTimeStamp = System.currentTimeMillis();
        System.out.println("Elapsed time" + (endTimeStamp - startTimeStamp));

    }

    public static void test() {
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("Alex");
//        for (int j = 0; j < 5000000; j++) {
//            System.out.println(syncObj.toString());
//        }
        //单线程 3466 29910
        //多 57319
        ImmutableObj immutableObj = new ImmutableObj("Fiona");
        for (int i = 0; i < 5000000; i++) {
            System.out.println(immutableObj.toString());
        }
        //单线程 3167 29379
        //多 56883
    }


}

class ImmutableObj {
    private final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "SyncObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
