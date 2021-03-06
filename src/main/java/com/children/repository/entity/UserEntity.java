package com.children.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author 孙博
 * date 2020/8/7 13:11
 */
@Data
@EqualsAndHashCode
public class UserEntity {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户真实姓名
     */
    private String username;

    /**
     * 用户性别 0男生 1 女生 2 保密
     */
    private Integer gender;

    /**
     * 用户生日
     */
    private String userBirthday;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户密码
     */
    private String userPasswordMd5;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户个性签名
     */
    private String userSignature;
}
