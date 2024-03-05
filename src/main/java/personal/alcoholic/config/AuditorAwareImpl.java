package personal.alcoholic.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import personal.alcoholic.domain.entity.User;

public class AuditorAwareImpl implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if(null == authentication || !authentication.isAuthenticated()) {
      return Optional.empty();
    }

    //사용자 환경에 맞게 로그인한 사용자의 정보를 불러온다.
    User userDetails = (User) authentication.getPrincipal();

    return Optional.of(userDetails.getUserId());
  }

}
