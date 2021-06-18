package com.kosta.finalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kosta.finalProject"})
public class KostaFitpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KostaFitpleApplication.class, args);
	}

}
