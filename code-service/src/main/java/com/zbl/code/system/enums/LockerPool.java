package com.zbl.code.system.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 19:58 2019/12/16
 * @Description: 神画 根据唯一标识创建对象池，给不同业务逻辑单独上锁(实现单例)
 * @Version: $
 */
public class LockerPool {
    private Map<String,String> lockMap = new HashMap<>();
    private static volatile LockerPool lockerPool = null;

    public String getLocker(String id){
        if(lockMap.get(id)==null){
            lockMap.put(id,"Lock"+id);
        }
        return lockMap.get(id);
    }

    private LockerPool(){

    }

    public static LockerPool getSingleton(){
        if(lockerPool == null){
            synchronized (LockerPool.class){
                if(lockerPool == null){
                    lockerPool = new LockerPool();
                }
            }
        }
        return lockerPool;
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 20).forEach(i -> new Thread(() -> {
            System.out.println(getSingleton());
        }).start());

    }
}
