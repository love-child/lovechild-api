package com.children.repository.dao.impl;

import com.children.repository.dao.BehaviorDao;
import com.children.repository.entity.BehaviorRecordEntity;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * author 孙博
 * date 2020/9/4 17:15
 */
@Repository
public class BehaviorDaoImpl implements BehaviorDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void addBehavior(BehaviorRecordEntity behaviorRecordEntity) {
        mongoTemplate.insert(behaviorRecordEntity);
    }

    @Override
    public List<BehaviorRecordEntity> findBehaviorRecordList(int skip, int limit) {
        BasicQuery basicQuery = new BasicQuery("{}");
        basicQuery.with(Sort.by(Sort.Direction.DESC, "create_time"));
        basicQuery.skip(skip)
                .limit(limit);
        return mongoTemplate.find(basicQuery, BehaviorRecordEntity.class);
    }

    @Override
    public List<String> findAvatarList() {
        BasicQuery basicQuery = new BasicQuery("{}", "{avatar:1,_id:0}");
        return mongoTemplate.find(basicQuery, Document.class, "user_avatar").stream().map(doc -> doc.getString("avatar")).collect(Collectors.toList());
    }
}
