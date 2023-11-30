package com.siseth.roi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server URL")
		}
)
@EnableFeignClients
@SpringBootApplication
public class RoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoiApplication.class, args);
	}

}
