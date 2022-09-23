package com.zbl.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2022/9/23 16:30
 * @Description:
 * @Version: $
 */
public class ZkServerHA1 {

    private String name;

    public ZkServerHA1(String name) {
        this.name = name;
    }

    static String status = "ACTIVE";

    public void run() {
        String conn = "192.168.25.141:2181,192.168.25.142:2181,192.168.25.143:2181";

        String ip = "8.136.133.137";
        Integer port = new Random().nextInt(500);
        System.out.println(port);

        try (ZooKeeper zk = new ZooKeeper(conn, 5000, watchedEvent -> System.out.println("process"))) {
//            **Watcher机制**
//            1、针对每个节点的操作，都会有一个监督者-> watcher
//            2、当监控的某个对象（znode）发生了变化，则触发watcher事件
//            3、zk中的watcher是一次性的，触发后立即销毁
//            4、父节点，子节点 增删改都能触发其watcher
//            5、针对不同类型的操作，触发的watcher事件也不同
//            1） （子）节点创建事件
//            2）（子）节点删除事件
//            3） （子）数据变化事件
            if (zk.exists("/zbl/server", event -> {
                System.out.println("serverName " + name + " " + event.getType().toString());
                //所有的观察者都会收到通知, 这时候只能一个观察者成为主
                if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                    status = "ACTIVE";
                    try {
                        zk.create("/zbl/server", (ip + ":" + port).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("serverName " + name + " 切换为active");
                }
            }) != null) {
                status = "STANDBY";
                System.out.println("serverName " + name + " 启动是STANDBY");
            } else {
                zk.create("/zbl/server", (ip + ":" + port).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                status = "ACTIVE";
                System.out.println("serverName " + name + " 启动就是active");
            }

            Thread.sleep(Integer.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ZkServerHA1("node1").run();
    }


}
