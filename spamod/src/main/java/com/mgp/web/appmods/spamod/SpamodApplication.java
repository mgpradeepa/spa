package com.mgp.web.appmods.spamod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAutoConfiguration
public class SpamodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpamodApplication.class, args);

	}

}

