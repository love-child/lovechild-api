package com.children.repository.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * author 孙博
 * date 2020/9/16 15:26
 */
@Data
@Document(collection = "robot_response_record")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RobotResponseRecord {

    private String id;

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private long createTime;

    @XStreamAlias("CreateTimeStr")
    private String createTimeStr;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("Content")
    private String content;

    @XStreamAlias("MsgId")
    private String msgId;
}
