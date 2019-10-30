package com.pramati.kart.productManagement.config;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.Collections;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections.*;
import javax.websocket.server.PathParam;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import static io.swagger.models.auth.In.HEADER;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return  new Docket(SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        ))
        .build()))
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.pramati.kart.productManagement.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());

    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Employee Management REST API")
                .contact(new Contact("Priyank", "www.pramati.com", "priyanktripathi007@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}