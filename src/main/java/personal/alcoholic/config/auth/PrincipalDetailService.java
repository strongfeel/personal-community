package personal.alcoholic.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal.alcoholic.model.User;
import personal.alcoholic.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
  // 해당 name이 DB에 있는지 확인
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User principle = userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + name));
    return new PrincipalDetail(principle); // 시큐리티 세션에 저장
  }
}
