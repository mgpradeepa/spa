package com.mgp.web.appmods.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
public class AuthsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthsecurityApplication.class, args);
	}

}
