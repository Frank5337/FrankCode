package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zbl
 * @Date: Created in 2023/1/28 10:43
 * @Description:
 *
 * /usr/java/jdk1.8.0_221-amd64/jre/bin/java -jar -Dspring.config.location=/home/c/application.yml code-mongodb-1.0-SNAPSHOT.jar >mongo.out 2>&1 &
 * @Version: $
 */
@SpringBootApplication(
//        exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class}
)
public class MongoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDBApplication.class, args);
    }

}
