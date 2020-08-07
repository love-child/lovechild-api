package com.children;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author 孙博
 * date 2020/8/6 15:19
 */
@EnableSwagger2
@SpringBootApplication
public class LoveChildApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoveChildApiApplication.class, args);
    }
}
