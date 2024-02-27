package personal.alcoholic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> { // 컨트롤러에서 응답을 보낼때 status와 data를 맵핑해서 리턴할 용도
  HttpStatus status;
  T data;
}
