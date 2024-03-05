package personal.alcoholic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import personal.alcoholic.domain.entity.User;
import personal.alcoholic.domain.util.Encryptor;
import personal.alcoholic.model.UserRole;

public class UserTest {

  private final Encryptor alwaysMatchEncryptor = new Encryptor() {
    @Override
    public String encrypt(String origin) {
      return origin;
    }

    @Override
    public boolean isMatch(String origin, String hashed) {
      return true;
    }
  };

  @Test
  void isMatch() {
    //given
    final User me = new User("test", "test", "test", "test", "01022222222",
        LocalDate.of(2023, 01, 01), UserRole.USER);
    //when

    //then
    assertTrue(me.isMatch(alwaysMatchEncryptor, "bbbbb"));

  }

}
