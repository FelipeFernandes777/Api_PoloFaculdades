package br.com.api.polofaculdades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PolofaculdadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolofaculdadesApplication.class, args);
	}

}
