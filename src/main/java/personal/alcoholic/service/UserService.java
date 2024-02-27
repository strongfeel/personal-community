package personal.alcoholic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.alcoholic.model.User;
import personal.alcoholic.model.UserRole;
import personal.alcoholic.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Transactional
  public void signUp(User user) {
    String rawPassword = user.getPassword();// 평문으로된 패스워드를 암호화 작업
    String encodePassword = encoder.encode(rawPassword);
    user.setPassword(encodePassword);
    user.setRole(UserRole.USER);
    userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public User findUser(String name) {
    User user = userRepository.findByName(name).orElseGet(User::new);
    return user;
  }

  @Transactional
  public void updateUserInfo(User user) {
    long id = user.getUserid();
    User currUser = userRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException(("Failed to load User Info : annot find User id")));

    //Validate
    if (currUser.getRole() == UserRole.USER) {
      String rawPassword = user.getPassword();
      String encodePassword = encoder.encode(rawPassword);
      currUser.setPassword(encodePassword); // 비밀번호 변경
    }
  }
}
