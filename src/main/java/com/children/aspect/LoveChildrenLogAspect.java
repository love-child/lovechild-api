package com.children.aspect;

import com.google.common.collect.Maps;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Map;

/**
 * author 孙博
 * date 2020/8/6 15:31
 */
@Aspect
@Component
public class LoveChildrenLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoveChildrenLogAspect.class);

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    @Pointcut("execution(public * com.children.controller.*Controller.*(..))")
    public void loveChildrenLog(){

    }

    @Around("loveChildrenLog()")
    public Object movieCommentLog(ProceedingJoinPoint pjp) throws Throwable{
        LocalDateTime startDate = LocalDateTime.now(ZoneId.of("+8"));
        String startTime = startDate.format(DateTimeFormatter.ofPattern(FORMAT));
        Long startTimestamp = startDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            String header = request.getHeader(element);
            System.out.println(element + "\t" + header);
        }
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        String[] paramNames = signature.getParameterNames();
        Object[] paramArgs = pjp.getArgs();
        String uri = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        Object proceed = pjp.proceed();
        LocalDateTime endDate = LocalDateTime.now(ZoneId.of("+8"));
        String endTime = endDate.format(DateTimeFormatter.ofPattern(FORMAT));
        Long endTimestamp = endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LOGGER.info("{}\tIP:{}\t 线程号:{}\t 参数:{}\t 开始时间:{}\t 结束时间:{} \t 接口耗时:{}", uri, ip(request), Thread.currentThread().getId(), getRequestParams(paramNames, paramArgs), startTime, endTime, endTimestamp - startTimestamp);
        return proceed;
    }


    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     *
     * @param paramsArgsName  方法参数名数组
     * @param paramsArgsValue 方法参数值数组
     */
    private static Map<String, Object> getRequestParams(String[] paramsArgsName, Object[] paramsArgsValue) {
        Map<String, Object> map = Maps.newHashMap();
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            return map;
        }
        for (int i = 0; i < paramsArgsName.length; i++) {
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];

            if (value instanceof MultipartFile){
                String originalFilename = ((MultipartFile) value).getOriginalFilename();
                map.put(name, originalFilename);
            }else if (value instanceof ServletResponse){
                map.put(name, "response");
            }else {
                map.put(name, value);
            }

        }
        return map;
    }


    private String ip(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return ip;
    }
}
