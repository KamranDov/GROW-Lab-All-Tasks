package az.grow.lab.jdbctemplatetask4.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String getMessage) {
        super(getMessage);
    }
}
