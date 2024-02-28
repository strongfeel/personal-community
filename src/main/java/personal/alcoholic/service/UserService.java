package personal.alcoholic.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.alcoholic.domain.dto.SignUpRequest;
import personal.alcoholic.domain.entity.User;
import personal.alcoholic.domain.repository.UserRepository;
import personal.alcoholic.domain.util.Encryptor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Encryptor encryptor;
  private final UserRepository userRepository;

  @Transactional
  public User create(SignUpRequest signUpRequest) {
    userRepository.findByEmail(signUpRequest.getEmail())
        .ifPresent(u -> {
          throw new RuntimeException("user already existed!");
        });
    return userRepository.save(new User(
        signUpRequest.getEmail(),
        encryptor.encrypt(signUpRequest.getPassword()),
        signUpRequest.getName(),
        signUpRequest.getNickname(),
        signUpRequest.getPhone(),
        signUpRequest.getBirth()
    ));
  }

  @Transactional
  public Optional<User> findPwMatchUser(String email, String password) {
    return userRepository.findByEmail(email)
        .map(user -> user.isMatch(encryptor, password) ? user : null);
  }
}
