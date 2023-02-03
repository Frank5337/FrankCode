package org.example.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Test;

/**
 * @Author: frank
 * @Date: Created in 2022/8/2 15:02
 * @Description:
 * @Version: $
 */
public class MongoDBTest4 {

    @Test
    public void test() {
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://test:test@192.168.230.232:1111/test"))) {
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