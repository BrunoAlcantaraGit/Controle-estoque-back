package com.controle.estoque;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class EstoqueApplication {
	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

}
