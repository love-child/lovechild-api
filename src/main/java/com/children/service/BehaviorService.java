package com.children.service;

import com.children.repository.entity.BehaviorRecordEntity;

import java.util.List;

/**
 * author 孙博
 * date 2020/9/4 18:40
 */
public interface BehaviorService {

    List<BehaviorRecordEntity> findList(int page, int size);
}
