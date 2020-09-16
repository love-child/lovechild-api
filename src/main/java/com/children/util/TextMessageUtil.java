package com.children.util;

import com.children.repository.entity.RobotResponseRecord;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * author 孙博
 * date 2020/9/16 16:00
 */
public class TextMessageUtil {
    /**
     * 将发送消息封装成对应的xml格式
     */
    public String messageToxml(RobotResponseRecord record) {
        XStream xstream  = new XStream();
        xstream.alias("xml", record.getClass());
        xstream.processAnnotations(record.getClass());
        return xstream.toXML(record);
    }
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param fromUserName
     * @param toUserName
     * @param content
     */
    public String initMessage(String fromUserName, String toUserName, String content) {
        RobotResponseRecord record = new RobotResponseRecord();
        record.setToUserName(fromUserName);
        record.setFromUserName(toUserName);
        record.setContent(content);
        record.setCreateTime(new Date().getTime());
        record.setMsgType("text");
        return messageToxml(record);
    }
}
