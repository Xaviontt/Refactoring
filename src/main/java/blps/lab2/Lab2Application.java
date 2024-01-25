package blps.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Lab2Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Lab2Application.class, args);
	}

}