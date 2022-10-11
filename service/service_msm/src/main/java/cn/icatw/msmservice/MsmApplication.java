package cn.icatw.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author icatw
 * @date 2022/10/11
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "cn.icatw")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
