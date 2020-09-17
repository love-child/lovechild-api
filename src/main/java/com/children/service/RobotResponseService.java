package com.children.service;

import com.children.repository.entity.RobotResponseRecord;

import java.util.Map;

/**
 * author 孙博
 * date 2020/9/16 15:20
 */
public interface RobotResponseService {

    String getRobotResponse(Map<String,String> map);
}
