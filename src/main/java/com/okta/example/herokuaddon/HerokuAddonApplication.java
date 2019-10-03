package com.okta.example.herokuaddon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HerokuAddonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerokuAddonApplication.class, args);
	}

}
