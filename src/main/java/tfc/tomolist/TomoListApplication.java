package tfc.tomolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class TomoListApplication {
	@Bean
    public Java8TimeDialect dialectoFechasTime() {
        return new Java8TimeDialect();
    }
	public static void main(String[] args) {
		SpringApplication.run(TomoListApplication.class, args);
	}

}
