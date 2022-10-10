package cn.icatw.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author icatw
 * @date 2022/10/10
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.icatw")
@MapperScan(basePackages = "cn.icatw.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
