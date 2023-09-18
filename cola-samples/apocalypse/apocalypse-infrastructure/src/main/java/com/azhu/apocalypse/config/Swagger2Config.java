package com.azhu.apocalypse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuliu
 * @date: 2020/11/3 12:28 下午
 * @description: Swagger2Config 配置类
 * ps http://localhost:8080/swagger-ui.html#!/  2.x
 * ps http://localhost:9080/swagger-ui/index.html 3.x
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(unifiedAuth())
                .securityContexts(Arrays.asList(securityContexts()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.azhu.apocalypse.web"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger接口文档")
                .description("接口文档")
                .version("1.0")
                .build();
    }

    private static List<SecurityScheme> unifiedAuth() {
        List<SecurityScheme> unifiedAuthList = new ArrayList<>();
        unifiedAuthList.add(new ApiKey("x-token", "x-token", "header"));
        return unifiedAuthList;
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 自动添加token 到请求头
     * 网上有更简介的写法
     * @return
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "描述信息");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> defaultAuthList = new ArrayList<>();
        // defaultAuth 和 unifiedAuth方法对应 Authorization 和 Permission 会被包装到请求体request的Header
        defaultAuthList.add(new SecurityReference("x-token", authorizationScopes));
        return defaultAuthList;
    }
}
