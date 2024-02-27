package personal.alcoholic.controller;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.alcoholic.config.auth.PrincipalDetailService;
import personal.alcoholic.dto.ResponseDto;
import personal.alcoholic.model.User;
import personal.alcoholic.service.UserService;

@RestController
public class UserController {

  private final UserService userService;

  private final PrincipalDetailService principalDetailService;

  public UserController(UserService userService, PrincipalDetailService principalDetailService) {
    this.userService = userService;
    this.principalDetailService = principalDetailService;
  }

  @PostMapping("/auth/joinProc") // 회원가입
  public ResponseDto<Boolean> save(@RequestBody User user) {
    userService.signUp(user);
    return new ResponseDto<>(HttpStatus.OK, true);
  }

  @PutMapping("/user") // 업데이트
  public ResponseDto<Boolean> update(@RequestBody User user, HttpSession session) {
    userService.updateUserInfo(user);
    UserDetails currUserDetails = principalDetailService.loadUserByUsername(user.getName());
    Authentication authentication =  new UsernamePasswordAuthenticationToken(
        currUserDetails, currUserDetails.getPassword(), currUserDetails.getAuthorities());
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);
    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    return new ResponseDto<>(HttpStatus.OK, true);
  }

  @GetMapping("/auth/username/{name}")
  public ResponseDto<Boolean> checkUserName(@PathVariable String name) {
    ResponseDto<Boolean> response = new ResponseDto<>(HttpStatus.OK, true);
    User selectedUser = userService.findUser(name);
    if (selectedUser.getName() != null) {
      response = new ResponseDto<>(HttpStatus.OK, false);
    }
    return response;
  }
}
