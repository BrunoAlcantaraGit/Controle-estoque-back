package com.controle.estoque;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;




@EnableFeignClients
@SpringBootApplication
public class EstoqueApplication {
	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}


}