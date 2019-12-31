package com.spryntas.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spryntas.api"))
                .paths(regex("/v1.*"))
                .build()
                .apiInfo(metaData());
	}
		
	@SuppressWarnings("deprecation")
	private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("SPRYNTAS REST API")
                .description("\"Spryntas Portal API\"")
                .version("1.0.0")
                .license("")
                .contact("shiddiq@spryntas.in")
                .build();
	    }
}
