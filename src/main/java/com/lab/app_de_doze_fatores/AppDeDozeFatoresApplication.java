package com.lab.app_de_doze_fatores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory;

@SpringBootApplication
public class AppDeDozeFatoresApplication {

	public static void main(String[] args) {
		var builder = new SpringApplicationBuilder(AppDeDozeFatoresApplication.class);
		builder
				.lazyInitialization(true)
				.build().run(AppDeDozeFatoresApplication.class, args);


	}

}
