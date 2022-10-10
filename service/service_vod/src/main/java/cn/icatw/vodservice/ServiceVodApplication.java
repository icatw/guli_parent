package cn.icatw.vodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "cn.icatw")
public class ServiceVodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }

}
