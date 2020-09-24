package com.children.service.impl;

import com.children.repository.dao.RobotResponseDao;
import com.children.repository.entity.RobotResponseRecord;
import com.children.service.RobotResponseService;
import com.children.util.RobotUtils;
import com.children.util.TextMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * author 孙博
 * date 2020/9/16 15:24
 */
@Service
public class RobotResponseServiceImpl implements RobotResponseService {

    private final RobotResponseDao robotResponseDao;
    private final RobotUtils robotUtils;

    @Autowired
    public RobotResponseServiceImpl(RobotResponseDao robotResponseDao, RobotUtils robotUtils) {
        this.robotResponseDao = robotResponseDao;
        this.robotUtils = robotUtils;
    }

    @Override
    public String getRobotResponse(Map<String,String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");
        if (Objects.equals("text", msgType)){
            RobotResponseRecord userResponseRecord = TextMessageUtil.initMessage(toUserName, fromUserName, content);
            robotResponseDao.addResponseRecord(userResponseRecord);
            String robotResponseMessage = robotUtils.getRobotResponseMessage(content, fromUserName);
            RobotResponseRecord robotResponseRecord = TextMessageUtil.initMessage(fromUserName, toUserName, robotResponseMessage);
            robotResponseDao.addResponseRecord(robotResponseRecord);
            return TextMessageUtil.messageToxml(robotResponseRecord);
        }
        RobotResponseRecord robotResponseRecord = TextMessageUtil.initDefaultMessage(fromUserName, toUserName);
        robotResponseDao.addResponseRecord(robotResponseRecord);
        return TextMessageUtil.messageToxml(robotResponseRecord);
    }
}
