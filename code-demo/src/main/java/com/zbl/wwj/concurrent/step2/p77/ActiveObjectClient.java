package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 14:30
 * @Description:
 * @Version: $
 */
public class ActiveObjectClient {

    /**
     * A a -> B b
     *
     * @param args
     */
    public static void main(String[] args) {
//        System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Ice", activeObject).start();
        new MakerClientThread("Fiona", activeObject).start();

        new DisplayClientThread("OTIS", activeObject).start();
    }

}
