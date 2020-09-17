package com.children.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

/**
 * author 孙博
 * date 2020/9/17 11:04
 */
@Component
public class RobotUtils {

    private static final String ROBOT_URL = "http://api.qingyunke.com/api.php";

    private static final String ROBOT_KEY = "free";

    private static final String ROBOT_APPID = "0";

    private static final String PARAM_FORMAT = "?key=%s&appid=%s&msg=%s";

    private static final List<String> RESPONSE_LIST = Lists.newArrayList("你在说什么呀 我听不懂", "你可太厉害了 把我都问倒了", "你的问题太多了 容我休息一会", "你好坏呀 我不理你了");

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
        String resultStr = null;
        try {
            String url = ROBOT_URL + String.format(PARAM_FORMAT, ROBOT_KEY, ROBOT_APPID, URLEncoder.encode(content, "utf-8"));
            String forObject = restTemplate.getForObject(url, String.class);
            Document document = Document.parse(forObject);
            resultStr = document.getString("content");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        int index = random.nextInt(RESPONSE_LIST.size());
        return resultStr == null ? RESPONSE_LIST.get(index) : resultStr;
    }
}
