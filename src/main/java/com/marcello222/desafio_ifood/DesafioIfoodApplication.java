package com.marcello222.desafio_ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DesafioIfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioIfoodApplication.class, args);
	}

}
