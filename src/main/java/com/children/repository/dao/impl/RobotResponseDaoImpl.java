package com.children.repository.dao.impl;

import com.children.repository.dao.RobotResponseDao;
import com.children.repository.entity.RobotResponseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * author 孙博
 * date 2020/9/16 15:25
 */
@Repository
public class RobotResponseDaoImpl implements RobotResponseDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public RobotResponseDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addResponseRecord(RobotResponseRecord record) {

    }
}
