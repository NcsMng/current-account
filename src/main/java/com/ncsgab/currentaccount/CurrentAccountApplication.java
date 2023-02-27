package com.ncsgab.currentaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CurrentAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrentAccountApplication.class, args);
	}

}
