package com.example.Aluguel.de.imoveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Aluguel.de.imoveis.repositories")
public class AluguelDeImoveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluguelDeImoveisApplication.class, args);
	}

}
