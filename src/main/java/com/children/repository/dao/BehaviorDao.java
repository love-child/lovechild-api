package com.children.repository.dao;


import com.children.repository.entity.BehaviorRecordEntity;

import java.util.List;
import java.util.Set;

/**
 * author 孙博
 * date 2020/9/4 16:49
 */
public interface BehaviorDao {

    /**
     * 添加用户动态
     * @param behaviorRecordEntity 动态
     */
    void addBehavior(BehaviorRecordEntity behaviorRecordEntity);

    /**
     * 查询动态列表
     * @return list
     */
    List<BehaviorRecordEntity> findBehaviorRecordList(int skip, int limit);


    List<String> findAvatarList();
}
