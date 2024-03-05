package personal.alcoholic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaAuditing
@RestController
public class AlcoholicApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlcoholicApplication.class, args);
  }

}
