package kameleoon.test.exception;



import lombok.*;

import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiError {

    private String message;
    private String reason;
    private HttpStatus status;

}
