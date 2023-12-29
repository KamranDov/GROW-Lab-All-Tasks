package az.grow.lab.springbootdemo2.exception;

public class ProductNotFoundException extends  RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }
}
