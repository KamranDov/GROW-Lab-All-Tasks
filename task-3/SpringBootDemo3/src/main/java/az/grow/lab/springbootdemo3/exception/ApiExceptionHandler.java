package az.grow.lab.springbootdemo3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public final ExceptionResponse productNotFound(Exception exception, WebRequest request){
        return new ExceptionResponse(LocalDateTime.now(),2002, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public final ExceptionResponse generalException(Exception exception, WebRequest request) {
        return new ExceptionResponse(LocalDateTime.now(), 3003, exception.getMessage());
    }
}