package com.bilibili;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class BilibiliApp {
    public static void main(String[] args) {
        log.info("【bilibili】 启动中...");
        ApplicationContext app = SpringApplication.run(BilibiliApp.class, args);
        log.info("【bilibili】 启动完成");
    }
}