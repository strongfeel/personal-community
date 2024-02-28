package personal.alcoholic.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.alcoholic.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByNickname(String nickname);
}
