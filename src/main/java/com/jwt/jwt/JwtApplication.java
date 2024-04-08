package com.jwt.jwt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "jwt",
				version = "1.0.0",
				description = "This project is for creating jwt token",
				termsOfService = "Copyright@2023",
				contact = @Contact(
						name = "Manjiri Chandure",
						email = "chanduremanjiri@gmail.com"
				)
		)
)
@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
