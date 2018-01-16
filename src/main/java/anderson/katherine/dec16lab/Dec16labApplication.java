package anderson.katherine.dec16lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories("anderson.katherine.dec16lab.repository")
//@EntityScan("anderson.katherine.dec16lab.model")
@SpringBootApplication
public class Dec16labApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dec16labApplication.class, args);
	}
}
