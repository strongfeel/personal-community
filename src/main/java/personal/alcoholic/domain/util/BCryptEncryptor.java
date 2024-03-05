package personal.alcoholic.domain.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncryptor implements Encryptor{

  @Override
  public String encrypt(String origin) {
    return BCrypt.hashpw(origin, BCrypt.gensalt());
  }

  @Override
  public boolean isMatch(String origin, String hashed) {
    try {
      return BCrypt.checkpw(origin, hashed);
    } catch (Exception e) {
      return false;
    }
  }
}
