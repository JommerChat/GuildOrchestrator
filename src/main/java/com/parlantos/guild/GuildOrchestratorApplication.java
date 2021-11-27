package com.parlantos.guild;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GuildOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuildOrchestratorApplication.class, args);
	}

}
