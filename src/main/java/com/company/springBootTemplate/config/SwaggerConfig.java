package com.company.springBootTemplate.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:deploy.properties")
public class SwaggerConfig {

    @Bean
    public Docket configureDocs() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis((RequestHandlerSelectors.basePackage("com.company.springBootTemplate.controller"))).build()
                .apiInfo(apiInfo("1.0-SNAPSHOT"))
                .tags(new Tag("Hello controller", "Primitive controller just to check the application state"));
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder()
                .title("Spring Boot Template App")
                .description("REST API for Spring Boot Template App")
                .termsOfServiceUrl("")
                .version(version)
                .build();
    }
}
