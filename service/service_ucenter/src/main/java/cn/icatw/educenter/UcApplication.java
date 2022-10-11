package cn.icatw.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author icatw
 * @date 2022/10/11
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootApplication
@ComponentScan("cn.icatw")
@MapperScan("cn.icatw.educenter.mapper")
public class UcApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcApplication.class, args);
    }
}
