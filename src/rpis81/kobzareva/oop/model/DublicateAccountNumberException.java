package rpis81.kobzareva.oop.model;

public class DublicateAccountNumberException extends Exception {
    public DublicateAccountNumberException(){
        super();
    }
    public DublicateAccountNumberException(String message) {
        super(message);
    }

    public DublicateAccountNumberException(String message, Throwable object) {
        super(message, object);
    }
}
