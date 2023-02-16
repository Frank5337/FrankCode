package org.example.service.impl;

import org.example.mongodb.dao.MongoDao;
import org.example.mongodb.pojo.Test;
import org.example.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: zbl
 * @Date: Created in 2023/2/7 16:51
 * @Description:
 * @Version: $
 */
@Service
public class MongoDBServiceImpl implements MongoDBService {

    @Autowired
    private MongoDao mongoDao;

    @Override
    public List<Test> findAll() {
        return mongoDao.findAll();
    }

    @Override
    public void add() {
        mongoDao.insert(new Test(UUID.randomUUID().toString()));
    }

}
