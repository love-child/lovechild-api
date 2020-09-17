package com.children.util;

import com.children.repository.entity.RobotResponseRecord;
import com.thoughtworks.xstream.XStream;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author 孙博
 * date 2020/9/16 16:00
 */
public class TextMessageUtil {
    /**
     * 将发送消息封装成对应的xml格式
     */
    public static String messageToxml(RobotResponseRecord record) {
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
    public static RobotResponseRecord initMessage(String fromUserName, String toUserName, String content) {
        RobotResponseRecord record = new RobotResponseRecord();
        record.setToUserName(fromUserName);
        record.setFromUserName(toUserName);
        record.setContent(content);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setCreateTime(date.getTime());
        record.setCreateTimeStr(simpleDateFormat.format(date));
        record.setMsgType("text");
        return record;
    }

    public static RobotResponseRecord initDefaultMessage(String fromUserName, String toUserName) {
        RobotResponseRecord record = new RobotResponseRecord();
        record.setToUserName(fromUserName);
        record.setFromUserName(toUserName);
        record.setContent("你在说什么呀 我听不懂");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setCreateTime(date.getTime());
        record.setCreateTimeStr(simpleDateFormat.format(date));
        record.setMsgType("text");
        return record;
    }
}
