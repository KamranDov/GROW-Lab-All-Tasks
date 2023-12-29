package az.grow.lab.jdbctemplatetask4.exception;

public class LoanGetByIdException extends  RuntimeException{
    public LoanGetByIdException(String message){
        super(message);
    }
}
