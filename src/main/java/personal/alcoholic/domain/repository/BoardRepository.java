package personal.alcoholic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import personal.alcoholic.domain.entity.Board;

@RepositoryRestResource
public interface BoardRepository extends JpaRepository<Board, Long> {

}
