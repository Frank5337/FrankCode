package org.example.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @Author: frank
 * @Date: Created in 2022/8/2 15:02
 * @Description:
 * @Version: $
 */
public class MongoDBTest3 {

    @Test
    public void test() {
//        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://test:test@192.168.96.160:30001,192.168.96.160:30002/test"))) {
//        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://test:test@192.168.96.160:30001,192.168.96.160:30002,192.168.96.160:30003/test"))) {
        for (int i = 0; i < 10; i++) {
            try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://192.168.96.47:27117,192.168.96.47:27118,192.168.96.47:27119/test"))) {

                MongoIterable<String> strings = mongoClient.listDatabaseNames();
                for (String string : strings) {
                    System.out.println(string);
                }
                System.out.println("================================");
                MongoDatabase database = mongoClient.getDatabase("test");
                ListCollectionsIterable<Document> documents = database.listCollections();
                for (Document document : documents) {
                    System.out.println(document);
                }
                System.out.println("================================");

//            BsonDocument bsonDocument = new BsonDocument();
//            bsonDocument.put("$db", new BsonString("local"));
//            Document document = database.runCommand(bsonDocument);
//            System.out.println(document);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test01() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("test", "test", "test".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("192.168.96.160", 27117),
                new ServerAddress("192.168.96.160", 27118),
                new ServerAddress("192.168.96.160", 27119)),
                Arrays.asList(credential, credential, credential), options);
        MongoIterable<String> strings = mongoClient.listDatabaseNames();
        for (String string : strings) {
            System.out.println(string);
        }

    }

    @Test
    @SuppressWarnings("all")
    public void test02() throws Exception {
        MongoClientOptions build = MongoClientOptions.builder().build();
        build.builder().minConnectionsPerHost(5).connectionsPerHost(10);
        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress("192.168.96.47", 27117),
                new ServerAddress("192.168.96.47", 27118),
                new ServerAddress("192.168.96.47", 27119)),
                build);

        MongoIterable<String> strings = mongoClient.listDatabaseNames();
        while (true) {
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

}