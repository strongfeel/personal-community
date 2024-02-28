package personal.alcoholic.domain.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import personal.alcoholic.domain.util.Encryptor;
import personal.alcoholic.model.UserRole;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userId;

  private String email;
  private String password;
  private String name;
  private String nickname;
  private String phone;
  private LocalDate birth;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  private LocalDateTime createdAt = LocalDateTime.now();

  public User(String email, String password, String name, String nickname, String phone,
      LocalDate birth) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.birth = birth;
  }

  public boolean isMatch(Encryptor encryptor, String password) {
    return encryptor.isMatch(password, this.password);
  }
}

