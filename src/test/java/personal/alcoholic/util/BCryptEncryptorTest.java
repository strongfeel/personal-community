package personal.alcoholic.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import personal.alcoholic.domain.util.BCryptEncryptor;
import personal.alcoholic.domain.util.Encryptor;

public class BCryptEncryptorTest {

  /*
  * password 암호화 테스트
  */
  @Test
  void test() {
      //given
      final String origin = "password";
      final Encryptor encryptor = new BCryptEncryptor();
      final String hash = encryptor.encrypt(origin);
      //when

      //then
      assertTrue(encryptor.isMatch(origin, hash));

      final String origin2 = "pass";
      assertFalse(encryptor.isMatch(origin2, hash));

  }
}
