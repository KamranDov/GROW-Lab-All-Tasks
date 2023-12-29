package az.grow.lab.jdbctemplatetask4.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private Integer statusCode;
    private String errorMessage;
}
