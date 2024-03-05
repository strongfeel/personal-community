package personal.alcoholic.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.alcoholic.domain.dto.LoginRequest;
import personal.alcoholic.domain.dto.SignUpRequest;
import personal.alcoholic.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

  private final LoginService loginService;

  @PostMapping("/api/sign_up")
  public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest, HttpSession session) {
    loginService.signUp(signUpRequest, session);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/api/login")
  public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
    loginService.login(loginRequest, session);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/api/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    loginService.logout(session);
    return ResponseEntity.ok().build();
  }

}
