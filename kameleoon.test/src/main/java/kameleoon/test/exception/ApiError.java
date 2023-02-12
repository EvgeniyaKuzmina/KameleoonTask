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
    private HttpStatus status;

}
