package org.example.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoIterable;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: frank
 * @Date: Created in 2022/8/2 15:02
 * @Description:
 * @Version: $
 */
@RunWith(SpringRunner.class)
// 指定一个随机端口。 这个注解它就可以加载整个 springboot 容器进来。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoDBTest extends TestCase {

    @Test
    public void test() {
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://test:test@192.168.96.160:30001"))) {
            MongoIterable<String> strings = mongoClient.listDatabaseNames();
            for (String string : strings) {
                System.out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}