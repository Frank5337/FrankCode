package org.example.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.example.mongodb.pojo.Test;
import org.example.service.MongoDBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2023/2/2 10:18
 * @Description:
 * @Version: $
 */
@Controller
public class MongoDBController {

    @Value("#{'${mongo.ipAddrs}'.split(',')}")
    private List<String> ipAddrs;

    @Value("#{'${mongo.ports}'.split(',')}")
    private List<Integer> ports;

    @GetMapping("/test")
    public void test() {
        if (ipAddrs.size() != ports.size()) {
            return;
        }
        List<ServerAddress> servers = new ArrayList<>();
        for (int i = 0; i < ipAddrs.size(); i++) {
            servers.add(new ServerAddress(ipAddrs.get(i), ports.get(i)));
        }
        MongoClientOptions build = MongoClientOptions.builder()
                .minConnectionsPerHost(5)
                .connectionsPerHost(100)
                .connectTimeout(1000 * 10)
                .build();
        MongoClient mongoClient = new MongoClient(servers, build);
        try {
            test01(mongoClient);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean onoff = false;

    @RequestMapping("/onoff")
    public void onoff() {
        onoff = !onoff;

    }

    public void test01(MongoClient mongoClient) throws Exception {
        MongoIterable<String> strings = mongoClient.listDatabaseNames();
        while (!onoff) {
            for (String schema : strings) {
                MongoDatabase mongoDatabase = mongoClient.getDatabase(schema);
                MongoIterable<String> cols = mongoDatabase.listCollectionNames();
                for (String col : cols) {
                    System.out.print(schema);
                    System.out.print("--");
                    System.out.print(col);
                    System.out.print("--");
                    MongoCollection<Document> documents = mongoDatabase.getCollection(col);
                    Document dbo = documents.find().first(); // 获取集合中最新一条数据
                    if (dbo != null) {
                        Iterator<String> iterator = dbo.keySet().iterator();
                        while (iterator.hasNext()) {
                            String coulm = iterator.next();
                            System.out.println(coulm);
                        }
                    }
                }
            }
        }

    }

    @Resource
    private MongoDBService mongoDBService;

    @RequestMapping("/test2")
    @ResponseBody
    public void test2(){
        run();
    }

    @RequestMapping("/bool2")
    @ResponseBody
    public void bool2(){
        flag = !flag;
    }

    private boolean flag = true;

    private void run(){
        while (flag) {
            try {
                List<Test> all = mongoDBService.findAll();
                System.out.println(all);
                mongoDBService.add();
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}
