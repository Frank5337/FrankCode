package com.zbl.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @Author: zbl
 * @Date: Created in 2022/9/23 16:30
 * @Description:
 * @Version: $
 */
public class ZkClient {

    public static void main(String[] args) {
        String conn = "192.168.25.141:2181,192.168.25.142:2181,192.168.25.143:2181";

        try (ZooKeeper zk = new ZooKeeper(conn, 5000, watchedEvent -> System.out.println("process"))) {

            Thread.sleep(2000);

            zk.create("/zbl", "zbltest".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
