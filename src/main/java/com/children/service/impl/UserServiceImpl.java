package com.children.service.impl;

import com.children.model.User;
import com.children.repository.entity.UserEntity;
import com.children.repository.mapper.UserMapper;
import com.children.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * author 孙博
 * date 2020/8/6 16:35
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
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
}
