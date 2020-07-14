package com.xxkj.swaggerdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zjx
 * @create 2020/7/14 15:39
 */
@SpringBootApplication
@MapperScan("com.xxkj.swaggerdemo.mapper")
@EnableSwagger2
public class SwaggerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerDemoApplication.class, args);
    }

}
