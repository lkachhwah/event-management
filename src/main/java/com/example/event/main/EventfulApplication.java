package com.example.event.main;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.event.pojo.Data;
import com.example.event.util.ConnectionsUtility;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EventfulApplication {

	public static Data data;

	public static void main(String[] args) {
		SpringApplication.run(EventfulApplication.class, args);
	}

	@PostConstruct
	public void load() {
		try {
			data = ConnectionsUtility.loadData();
		} catch (Exception e) {
			System.out.println(
					"There is some issue while loading application, Please check network connection with eventful.com");
		}
	}

	@Bean
	public Docket eventApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.event"))
				 .paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Event Management Rest APIs")
				.description("This page lists all the rest apis for Event Management app.").version("1.0-SNAPSHOT")
				.build();
	}
}
