package org.example.mongodb.dao;

import org.example.mongodb.pojo.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zbl
 * @Date: Created in 2023/2/7 16:45
 * @Description:
 * @Version: $
 */
@Repository
public interface MongoDao extends MongoRepository<Test, Long> {

}
