package br.edu.ifsp.arq.exape_dw2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class ExapeDw2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExapeDw2Application.class, args);
	}

}
