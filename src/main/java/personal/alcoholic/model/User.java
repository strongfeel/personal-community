package personal.alcoholic.model;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userid;

  @Column(nullable = false, length = 50, unique = true)
  private String email;

  @Column(length = 50)
  private String password;

  @Column(nullable = false, length = 15)
  private String name;

  @Column(nullable = false, length = 15, unique = true)
  private String nickname;

  private String phone;

  private LocalDate birth;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @CreatedDate
  @DateTimeFormat(pattern = "yyyy-MM--dd")
  private LocalDate createdAt;


}

