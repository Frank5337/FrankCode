package org.example.service;

import org.example.mongodb.pojo.Test;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2023/2/7 16:37
 * @Description:
 * @Version: $
 */
@Service
public interface MongoDBService {

    List<Test> findAll();

    void add();



}
