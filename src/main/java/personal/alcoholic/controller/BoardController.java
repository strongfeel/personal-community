package personal.alcoholic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import personal.alcoholic.domain.dto.BoardRequest;
import personal.alcoholic.domain.entity.User;
import personal.alcoholic.service.BoardService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  //게시글 작성
  @PostMapping("/api/board/write")
  public ResponseEntity<?> saveBoard(@Validated @RequestBody BoardRequest boardRequest,
      @SessionAttribute(name = "userId", required = false) User loginUser) {

    // 로그인 확인
    if (loginUser == null) {
      return new ResponseEntity<>("로그인 후, 다시 시도해 주세요.", HttpStatus.BAD_REQUEST);
    }

    boardService.writeBoard(boardRequest, loginUser);
    return new ResponseEntity<>("게시글 작성이 완료됬습니다.", HttpStatus.OK);
  }

  // 게시글 삭제
  @DeleteMapping("/api/board/delete/{boardId}")
  public ResponseEntity<?> deleteBoard(@PathVariable("boardId") long boardId,
      @SessionAttribute(name = "userId", required = false) User loginUser) {

    // 로그인 확인
    if (loginUser == null) {
      return new ResponseEntity<>("로그인 후, 다시 시도해 주세요.", HttpStatus.BAD_REQUEST);
    }

    boardService.deleteBoard(boardId, loginUser);
    return new ResponseEntity<>("게시글 삭제가 완료되었습니다.", HttpStatus.OK);
  }

  // 게시글 업데이트
  @PutMapping("/api/board/update")
  public ResponseEntity<?> updateBoard(@Validated @RequestBody BoardRequest boardRequest,
      @SessionAttribute(name = "userId", required = false) User loginUser) {

    // 로그인 확인
    if (loginUser == null) {
      return new ResponseEntity<>("로그인 후, 다시 시도해 주세요.", HttpStatus.BAD_REQUEST);
    }

    boardService.updateBoard(boardRequest, loginUser);
    return new ResponseEntity<>("게시글 수정이 완료 되었습니다.", HttpStatus.OK);
  }

  //특정 게시글 출력
  @GetMapping("/api/board/view/{boardId}")
  public ResponseEntity<?> getBoardDetail(@PathVariable long boardId) {

    boardService.getBoardDetail(boardId);
    return new ResponseEntity<>("특정 게시글을 불러왔습니다.", HttpStatus.OK);
  }

  // 모든 게시글 출력
  @GetMapping("/api/board/view")
  public ResponseEntity<?> getBoardList() {

    boardService.getBoardList();
    return new ResponseEntity<>("게시글을 불러 왔습니다.", HttpStatus.OK);
  }
}
