package personal.alcoholic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
public class JpaAuditConfig {

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return new AuditorAwareImpl();
  }

}
