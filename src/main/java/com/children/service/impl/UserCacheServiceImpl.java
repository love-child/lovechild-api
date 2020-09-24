package com.children.service.impl;

import com.children.repository.dao.BehaviorDao;
import com.children.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author 孙博
 * date 2020/9/24 14:15
 */
@Component
public class UserCacheServiceImpl implements UserCacheService {

    private final BehaviorDao behaviorDao;

    @Autowired
    public UserCacheServiceImpl(BehaviorDao behaviorDao) {
        this.behaviorDao = behaviorDao;
    }

    @Override
    @Cacheable(value = "getAllUserAvatar", key = "'user::all::avatar'")
    public List<String> getAllUserAvatar() {
        return behaviorDao.findAvatarList();
    }
}
