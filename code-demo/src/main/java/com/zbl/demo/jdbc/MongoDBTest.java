package com.zbl.demo.jdbc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/16 15:26
 * @Description:
 * @Version: $
 */
public class MongoDBTest {

    private static final String COLUMN_PRIMARY_KEY = "_id";

    public static void main(String[] args) {
        //mongodb://%s" + ip + ":" + port
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:123456@192.168.202.2:27017"));

        MongoIterable<String> dbnames = mongoClient.listDatabaseNames();
        for (String dbname : dbnames) {
            if (("admin".equals(dbname) || "config".equals(dbname) || "local".equals(dbname))) {
                continue;
            }
            MongoDatabase mongoDatabase = mongoClient.getDatabase(dbname);
            MongoIterable<String> cols = mongoDatabase.listCollectionNames();
            for (String tableName : cols) {
                System.out.println(dbname + "--" + tableName);

            }
        }
    }

    private static String getType(Object columValue) {
        if (columValue instanceof String) {
            return "string";
        }
        if (columValue instanceof Timestamp) {
            return "timestamp";
        }
        if (columValue instanceof Date) {
            return "date";
        }
        if (columValue instanceof Integer) {
            return "integer ";
        }
        if (columValue instanceof Double) {
            return "double";
        }
        if (columValue instanceof Arrays) {
            return "arrays";
        }
        if (columValue instanceof Boolean) {
            return "boolean";
        }
        if (columValue instanceof Collection) {
            return "collection";
        }
        return "unknown";
    }

}
