package com.xxkj.resume.resume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author zjx
 * @create 2020/3/20 10:04
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.xxkj.myzone.mapper")
public class MyzoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyzoneApplication.class, args);
    }
}
