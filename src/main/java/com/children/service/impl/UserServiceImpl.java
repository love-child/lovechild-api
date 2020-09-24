package com.children.service.impl;

import com.children.model.User;
import com.children.repository.entity.UserEntity;
import com.children.repository.mapper.UserMapper;
import com.children.service.UserCacheService;
import com.children.service.UserService;
import com.children.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * author 孙博
 * date 2020/8/6 16:35
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserCacheService userCacheService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserCacheService userCacheService) {
        this.userMapper = userMapper;
        this.userCacheService = userCacheService;
    }

    @Override
    public User login(String userAccount, String userPassword) {
        UserEntity userEntity = userMapper.getUserByUserAccount(userAccount);
        if (userEntity == null || !Objects.equals(userPassword, userEntity.getUserPassword())){
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public User register(String userAccount, String userPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserAccount(userAccount);
        userEntity.setUserPassword(userPassword);
        userEntity.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userEntity.setGender(2);
        userEntity.setNickname("没有名字的小可爱");
        userEntity.setUsername("没有名字的小可爱");
        List<String> allUserAvatar = userCacheService.getAllUserAvatar();
        Random random = new Random();
        int index = random.nextInt(allUserAvatar.size());
        userEntity.setUserAvatar(allUserAvatar.get(index));
        userEntity.setUserPasswordMd5(MD5Utils.md5(userPassword));
        userMapper.insertUser(userEntity);
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }
}
