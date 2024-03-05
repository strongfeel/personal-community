package personal.alcoholic.domain.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
  @Column(length = 15)
  private String name;
  @Column(length = 15)
  private String nickname;
  @Column(length = 20)
  private String phone;
  private LocalDate birth;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  private LocalDateTime createdAt = LocalDateTime.now();

  public User(String email, String password, String name, String nickname, String phone,
      LocalDate birth, UserRole role) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.birth = birth;
    this.role = role;
  }

  public boolean isMatch(Encryptor encryptor, String password) {
    return encryptor.isMatch(password, this.password);
  }
}

