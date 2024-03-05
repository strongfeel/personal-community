package personal.alcoholic.service;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.alcoholic.domain.dto.LoginRequest;
import personal.alcoholic.domain.dto.SignUpRequest;
import personal.alcoholic.domain.entity.User;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final UserService userService;
  private final String LOGIN_SESSION_KEY = "userId";

  @Transactional
  public void signUp(SignUpRequest signUpRequest, HttpSession session) {
    final User user = userService.create(signUpRequest);
    session.setAttribute(LOGIN_SESSION_KEY, user.getUserId());
  }

  @Transactional
  public void login(LoginRequest loginRequest, HttpSession session) {
    final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
    if (userId != null) {
      return;
    }
    final Optional<User> user = userService.findPwMatchUser(loginRequest.getEmail(),
        loginRequest.getPassword());
    if (user.isPresent()) {
      session.setAttribute(LOGIN_SESSION_KEY, user.get().getUserId());
    } else {
      throw new RuntimeException("비밀번호와 이메일이 일치하지 않습니다.");
    }
  }

  public void logout(HttpSession session) {
    session.removeAttribute(LOGIN_SESSION_KEY);
  }

}
