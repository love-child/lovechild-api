package com.children.service;

import com.children.model.User;

/**
 * author 孙博
 * date 2020/8/6 16:34
 */
public interface UserService {

    /**
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    User login(String userAccount, String userPassword);

    /**
     * 用户注册接口
     * @param userAccount
     * @param userPassword
     * @return
     */
    User register(String userAccount, String userPassword);
}
