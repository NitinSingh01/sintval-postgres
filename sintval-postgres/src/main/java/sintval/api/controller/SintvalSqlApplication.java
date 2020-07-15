package sintval.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "sintval.api.controller","sintval.api.entity","sintval.api.exception","sintval.api.repository","sintval.api.error"} )
@EnableJpaRepositories(basePackages = {"sintval.api.repository"})
@EntityScan(basePackages = {"sintval.api.entity"})
public class SintvalSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SintvalSqlApplication.class, args);
	}

}
