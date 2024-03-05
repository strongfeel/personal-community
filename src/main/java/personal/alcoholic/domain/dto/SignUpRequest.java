package personal.alcoholic.domain.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SignUpRequest {

  private final String email;
  private final String password;
  private final String nickname;
  private final String name;
  private final String phone;
  private final LocalDate birth;

}
