package com.children.service;

import java.util.List;

/**
 * author 孙博
 * date 2020/9/24 14:13
 */
public interface UserCacheService {

    /**
     * 获取所有用户头像集合
     * @return
     */
    List<String> getAllUserAvatar();
}
