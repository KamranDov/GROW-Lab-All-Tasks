package az.ibrahimshirinov.jdbctemplatesample.exception;

import az.ibrahimshirinov.jdbctemplatesample.exception.model.ErrorResponse;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException ex) {
        var error = new ErrorResponse(UUID.randomUUID(), HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        var error = new ErrorResponse(UUID.randomUUID(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                null, LocalDateTime.now());

        ex.getFieldErrors().forEach(fieldError -> error.getFieldErrors()
                .add(new ErrorResponse.FieldError(fieldError.getField(),
                        fieldError.getDefaultMessage())));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
