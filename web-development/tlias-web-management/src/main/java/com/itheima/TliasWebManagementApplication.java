package com.itheima;

import com.itheima.pojo.AliYunOss;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties( AliYunOss.class)
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}
