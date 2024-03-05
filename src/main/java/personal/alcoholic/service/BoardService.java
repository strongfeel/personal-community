package personal.alcoholic.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.alcoholic.domain.dto.BoardRequest;
import personal.alcoholic.domain.dto.BoardResponse;
import personal.alcoholic.domain.entity.Board;
import personal.alcoholic.domain.entity.User;
import personal.alcoholic.domain.repository.BoardRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

  private final BoardRepository boardRepository;

  @Transactional
  public Board writeBoard(BoardRequest boardRequest, User user) {
    return boardRepository.save(Board.builder()
        .title(boardRequest.getTitle())
        .content(boardRequest.getContent())
        .user(user)
        .build());
  }

  @Transactional(readOnly = true)
  public void getBoardList() {
    List<Board> boardList = boardRepository.findAll();
    List<BoardResponse> boardDtoList = new ArrayList<>();

    for (Board board : boardList) {
      BoardResponse boardDto = BoardResponse.builder()
          .title(board.getTitle())
          .content(board.getContent())
          .userNickname(board.getUser().getNickname())
          .createdAt(board.getCreatedAt())
          .build();
      boardDtoList.add(boardDto);
    }
  }

  @Transactional(readOnly = true)
  public void getBoardDetail(long bordId) {
    Board boardDetail = boardRepository.findById(bordId)
        .orElseThrow(() -> new IllegalStateException("게시글을 불러오는데 실패했습니다."));

    BoardResponse.builder()
        .title(boardDetail.getTitle())
        .content(boardDetail.getContent())
        .userNickname(boardDetail.getUser().getNickname())
        .createdAt(boardDetail.getCreatedAt())
        .modifiedAt(boardDetail.getModifiedAt())
        .build();
  }

  @Transactional
  public void deleteBoard(long boardId, User user) {

    Board deleteBoard = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalStateException("게시글이 없습니다."));

    User writer = deleteBoard.getUser();

    if (user == writer) {
      boardRepository.deleteById(boardId);
    }

  }

  @Transactional
  public void updateBoard(BoardRequest requestBoard, User user) {
    Board updateBoard = boardRepository.findById(requestBoard.getBoardId())
        .orElseThrow(() -> new IllegalStateException("게시글을 업데이트 하는데 실패했습니다."));

    User writer = updateBoard.getUser();

    if (user == writer) {
      updateBoard.setTitle(requestBoard.getTitle());
      updateBoard.setContent(requestBoard.getContent());
    }

  }
}
