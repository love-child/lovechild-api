package com.children.service.impl;

import com.children.repository.dao.BehaviorDao;
import com.children.repository.entity.BehaviorRecordEntity;
import com.children.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * author 孙博
 * date 2020/9/4 18:41
 */
@Service
public class BehaviorServiceImpl implements BehaviorService {

    private final BehaviorDao behaviorDao;

    private static volatile List<String> avatarSet;

    @Autowired
    public BehaviorServiceImpl(BehaviorDao behaviorDao) {
        this.behaviorDao = behaviorDao;
    }

    @Override
    public List<BehaviorRecordEntity> findList(int page, int size) {
        if (avatarSet == null){
            avatarSet = behaviorDao.findAvatarList();
        }
        List<BehaviorRecordEntity> behaviorRecordList = behaviorDao.findBehaviorRecordList(page * size, size);
        Random random = new Random();
        behaviorRecordList.forEach(behaviorRecordEntity -> {
            int index = random.nextInt(avatarSet.size());
            String avatar = avatarSet.get(index);
            behaviorRecordEntity.setUserAvatar(avatar);
        });
        return behaviorRecordList;
    }
}
