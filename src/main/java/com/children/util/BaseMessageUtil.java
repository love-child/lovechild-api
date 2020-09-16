package com.children.util;

/**
 * author 孙博
 * date 2020/9/16 16:16
 */
public interface BaseMessageUtil <T>{
    /**
     * 将回复的信息对象转xml格式给微信
     * @param t
     * @return
     */
    String messageToxml(T t);

    /**
     * 回复的信息封装
     * @param FromUserName
     * @param ToUserName
     * @return
     */
    String initMessage(String FromUserName,String ToUserName);
}
