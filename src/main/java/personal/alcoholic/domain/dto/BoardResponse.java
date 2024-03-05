package personal.alcoholic.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponse {

  private String title;

  private String content;

  private String userNickname;

  private LocalDateTime createdAt;

  private LocalDateTime modifiedAt;


}
