package cn.icatw.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author icatw
 * @date 2022/9/8
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.icatw.*"})
@EnableDiscoveryClient
@EnableFeignClients
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);

    }
}
