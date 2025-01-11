package com.controle.estoque;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(servers = {
		@Server(url = "https://controle-estoque-back-production.up.railway.app", description = "Railway Server"),
		@Server(url = "http://localhost:3000", description = "Local Server")
})
public class EstoqueApplication {

	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

}
