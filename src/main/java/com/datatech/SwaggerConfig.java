package com.datatech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger-ui配置
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.d3.kglearn.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("api")
				.termsOfServiceUrl("houfangqing")
				.description("联系qq704237006")
				.contact(new Contact("houfangqing", "联系qq704237006", "704237006@qq.com"))
				.version("1.0.0")
				.build();
	}
}
