package com.children.repository.mapper;

import com.children.repository.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * author 孙博
 * date 2020/8/7 13:11
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户账号获取用户信息
     * @param userAccount 账号
     * @return
     */
    @Select("SELECT * FROM user WHERE user_account = #{userAccount}")
    UserEntity getUserByUserAccount(String userAccount);

    @Insert("INSERT INTO `love_baby`.`user`(`user_id`, `nickname`, `user_phone`, `username`, `gender`, `user_birthday`, `user_account`, `user_password`, `user_password_md5`, `user_avatar`) VALUES (#{userId}, #{nickname}, NULL, #{username}, 2, NULL, #{userAccount}, #{userPassword}, #{userPasswordMd5}, #{userAvatar})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertUser(UserEntity userEntity);
}
