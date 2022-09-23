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
public class ZkServerHA3 {

    private String name;

    public ZkServerHA3(String name) {
        this.name = name;
    }

    static String status = "ACTIVE";

    public void run() {
        String conn = "192.168.25.141:2181,192.168.25.142:2181,192.168.25.143:2181";

        String ip = "8.136.133.137";
        Integer port = new Random().nextInt(500);
        System.out.println(port);

        try (ZooKeeper zk = new ZooKeeper(conn, 5000, watchedEvent -> System.out.println("process"))) {

            if (zk.exists("/zbl/server", event -> {
                System.out.println("serverName " + name + " " + event.getType().toString());
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
        new ZkServerHA1("node3").run();
    }


}
