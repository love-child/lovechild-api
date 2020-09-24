package com.children.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

/**
 * author 孙博
 * date 2020/9/17 11:04
 */
@Component
public class RobotUtils {

//    private static final String ROBOT_URL = "http://api.qingyunke.com/api.php";
//
//    private static final String ROBOT_KEY = "free";
//
//    private static final String ROBOT_APPID = "0";
//
    private static final String PARAM_FORMAT = "?key=%s&appid=%s&msg=%s";

    private static final List<String> RESPONSE_LIST = Lists.newArrayList("你在说什么呀 我听不懂", "你可太厉害了 把我都问倒了", "你的问题太多了 容我休息一会", "你好坏呀 我不理你了");

    private static final String ROBOT_URL = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";

    private static final String ROBOT_KEY = "free";

    private static final String ROBOT_APPID = "2156862408";
    private static final String ROBOT_APPKEY = "mBAQlKD8AUHVQ6Yv";

    private final RestTemplate restTemplate;

    @Autowired
    public RobotUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取智能回复
     * @param content 内容
     * @return 回复
     */
    public String getRobotResponseMessage(String content) {
        long l = System.currentTimeMillis();
        String resultStr = null;
        try {
            String url = ROBOT_URL + String.format(PARAM_FORMAT, ROBOT_KEY, ROBOT_APPID, URLEncoder.encode(content, "utf-8"));
            String forObject = restTemplate.getForObject(url, String.class);
            Document document = Document.parse(forObject);
            resultStr = document.getString("content");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("机器人花费时长" + (System.currentTimeMillis() - l));
        Random random = new Random();
        int index = random.nextInt(RESPONSE_LIST.size());
        return resultStr == null ? RESPONSE_LIST.get(index) : resultStr;
    }

    /**
     * 获取智能回复
     * @param content 内容
     * @return 回复
     */
    public String getRobotResponseMessage(String content, String fromUserName) {
        String timestamp = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000);
        String params = null;
        MultiValueMap<String,String> paramsMap = new LinkedMultiValueMap<>();
        try {
            params = params(paramsMap, timestamp, UUID.randomUUID().toString().replace("-", ""), fromUserName, content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        int index = random.nextInt(RESPONSE_LIST.size());
        if (params == null){
            return RESPONSE_LIST.get(index);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity(paramsMap, headers);
        Map<String,Object> map = restTemplate.postForObject(ROBOT_URL, httpEntity, Map.class, params);
        return map != null && "0".equals(map.get("ret").toString()) && map.get("answer") != null && ((Map<String,Object>)map.get("data")).get("answer") != null ? ((Map<String,Object>)map.get("data")).get("answer").toString() : RESPONSE_LIST.get(index);
    }
    private String params(MultiValueMap<String,String> params, String timestamp, String nonceStr, String session, String question) throws UnsupportedEncodingException {
        params.add("app_id", ROBOT_APPID);
        params.add("time_stamp", timestamp);
        params.add("nonce_str", nonceStr);
        params.add("session", session);
        params.add("question", question);
        List<String> keys = Lists.newArrayList(params.keySet());
        Collections.sort(keys);
        final String SEPARATOR = "&";
        final String EQUAL = "=";
        StringBuilder paramStr = new StringBuilder();
        for (String key : keys) {
            String value = params.getFirst(key);
            if (StringUtils.isEmpty(value)){
                continue;
            }
            paramStr.append(key).append(EQUAL).append(URLEncoder.encode(value, "utf-8")).append(SEPARATOR);
        }
        paramStr.append("app_key").append(EQUAL).append(URLEncoder.encode(ROBOT_APPKEY, "utf-8"));
        String md5 = MD5Utils.md5(paramStr.toString());
        params.add("sign", md5.toUpperCase());
        return md5.toUpperCase();
    }

}
