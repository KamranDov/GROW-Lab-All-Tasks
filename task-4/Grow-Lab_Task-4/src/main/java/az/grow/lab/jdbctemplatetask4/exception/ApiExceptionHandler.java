package az.grow.lab.jdbctemplatetask4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(QueryNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ExceptionResponse queryNull(Exception e, WebRequest webRequest) {
        return new ExceptionResponse(LocalDateTime.now(), 1000, e.getMessage());
    }

    @ExceptionHandler(NotApprovedException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public final ExceptionResponse notApproved(Exception e, WebRequest webRequest) {
        return new ExceptionResponse(LocalDateTime.now(), 1001, e.getMessage());
    }

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ExceptionResponse loanNotFound(Exception e, WebRequest webRequest) {
        return new ExceptionResponse(LocalDateTime.now(), 1002, e.getMessage());
    }

    @ExceptionHandler(ProductListException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionResponse productList(Exception e, WebRequest webRequest) {
        return new ExceptionResponse(LocalDateTime.now(), 1003, e.getMessage());
    }

    @ExceptionHandler(LoanGetByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ExceptionResponse getById(Exception e, WebRequest webRequest) {
        return new ExceptionResponse(LocalDateTime.now(), 1004, e.getMessage());
    }

}
