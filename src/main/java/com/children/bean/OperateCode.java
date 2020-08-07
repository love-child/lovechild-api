package com.children.bean;

/**
 * author 孙博
 * date 2020/8/6 15:51
 */
public enum OperateCode {
    SUCCESS("0000", "操作成功"),
    SYSTEM_ERROR("9999", "系统错误"),
    ;

    private String operateCode;
    private String operateMsg;

    OperateCode(String operateCode, String operateMsg) {
        this.operateCode = operateCode;
        this.operateMsg = operateMsg;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public String getOperateMsg() {
        return operateMsg;
    }
}
