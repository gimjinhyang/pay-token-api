package pay.token.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * 에러 응답 모델
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    @JsonProperty("status")
    private int status = HttpStatus.NOT_FOUND.value();

    @JsonProperty("message")
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

}
