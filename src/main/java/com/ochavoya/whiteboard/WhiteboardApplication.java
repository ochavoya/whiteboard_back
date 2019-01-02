package com.ochavoya.whiteboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class WhiteboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteboardApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}

