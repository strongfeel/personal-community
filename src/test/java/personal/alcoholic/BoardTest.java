package personal.alcoholic;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import personal.alcoholic.domain.dto.BoardRequest;
import personal.alcoholic.domain.entity.Board;
import personal.alcoholic.domain.entity.User;
import personal.alcoholic.domain.repository.BoardRepository;
import personal.alcoholic.domain.repository.UserRepository;
import personal.alcoholic.model.UserRole;
import personal.alcoholic.service.BoardService;
import personal.alcoholic.service.UserService;

@SpringBootTest
public class BoardTest {

  @Autowired
  UserService userService;

  @Autowired
  BoardService boardService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BoardRepository boardRepository;

  public static BoardRequest boardRequest(Long boardId, String title, String content) {
    return BoardRequest.builder()
        .boardId(boardId)
        .title(title)
        .content(content)
        .build();
  }


  @Test
  @DisplayName("게시글 작성 성공")
  void writeTest() {
    //given
    User user = userRepository.save(
        new User("email", "password", "name", "nickname", "phone", LocalDate.of(2024, 01, 01),
            UserRole.USER));
    BoardRequest boardRequest = boardRequest(0L, "title", "content");
    //when
    Board board = boardService.writeBoard(boardRequest, user);
    //then
    assertThat(board.getBoardId()).isEqualTo(0L);
    assertThat(board.getTitle()).isEqualTo("title");
    assertThat(board.getContent()).isEqualTo("content");
  }

}
