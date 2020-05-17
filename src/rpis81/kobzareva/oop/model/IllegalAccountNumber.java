package rpis81.kobzareva.oop.model;

public class IllegalAccountNumber extends RuntimeException{
    public IllegalAccountNumber(String message){
        super(message);
    }
}
