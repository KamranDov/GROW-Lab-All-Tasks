package az.grow.lab.springbootdemo3.exception;

public class ProductNotFoundException extends  RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }
}
