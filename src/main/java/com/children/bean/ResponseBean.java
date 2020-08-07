package com.children.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * author 孙博
 * date 2020/8/6 15:48
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseBean<T> {

    private String operateCode;

    private String operateMsg;

    private T operateResult;

    private Long operateTimestamp;

    public ResponseBean(){
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResponseBean(OperateCode operateCode) {
        this.operateCode = operateCode.getOperateCode();
        this.operateMsg = operateCode.getOperateMsg();
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResponseBean(OperateCode operateCode, T operateResult) {
        this.operateCode = operateCode.getOperateCode();
        this.operateMsg = operateCode.getOperateMsg();
        this.operateResult = operateResult;
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static <T> ResponseBean<T> success(){
        return success(null);
    }

    public static <T> ResponseBean<T> success(T content){
        return set(OperateCode.SUCCESS, content);
    }

    public static <T> ResponseBean<T> set(OperateCode operateCode, T content){
        return new ResponseBean<>(operateCode, content);
    }
}
