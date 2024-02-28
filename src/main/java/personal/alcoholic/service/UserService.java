package personal.alcoholic.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.alcoholic.domain.dto.SignUpRequest;
import personal.alcoholic.domain.entity.User;
import personal.alcoholic.domain.repository.UserRepository;
import personal.alcoholic.domain.util.Encryptor;
import personal.alcoholic.model.UserRole;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Encryptor encryptor;
  private final UserRepository userRepository;

  @Transactional
  public User create(SignUpRequest signUpRequest) {

    // 이메일 중복 검사
    userRepository.findByEmail(signUpRequest.getEmail())
        .ifPresent(user -> {
          throw new RuntimeException("이미 존재하는 이메일 입니다.");
        });

    // 닉네임 중복 검사
    userRepository.findByNickname(signUpRequest.getNickname())
        .ifPresent(user -> {
          throw new RuntimeException("이미 존재하는 닉네임 입니다.");
        });

    // 회원가입
    return userRepository.save(new User(
        signUpRequest.getEmail(),
        encryptor.encrypt(signUpRequest.getPassword()),
        signUpRequest.getName(),
        signUpRequest.getNickname(),
        signUpRequest.getPhone(),
        signUpRequest.getBirth()
        )
    );
  }

  // 이메일 비밀번호 일치 확인
  @Transactional
  public Optional<User> findPwMatchUser(String email, String password) {
    return userRepository.findByEmail(email)
        .map(user -> user.isMatch(encryptor, password) ? user : null);
  }
}
