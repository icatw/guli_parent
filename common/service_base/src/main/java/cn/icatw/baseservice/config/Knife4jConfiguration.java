package cn.icatw.baseservice.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 配置
 *
 * @author 76218
 * @date 2022/09/09
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范、
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("# icatw RESTful APIs")
                        .termsOfServiceUrl("http://www.icatw.top/")
                        .contact("762188827@qq.com")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("在线教育网站")
                .select()
                //这里指定Controller扫描包路径
                //.apis(RequestHandlerSelectors.basePackage("cn.icatw.eduservice.controller"))
                // 只有标记了@Api的类方法才会暴露出给swagger
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 只有标记了@ApiOperation的方法才会暴露出给swagger
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
