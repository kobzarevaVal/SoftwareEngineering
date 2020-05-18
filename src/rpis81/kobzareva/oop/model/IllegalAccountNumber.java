package rpis81.kobzareva.oop.model;

public class IllegalAccountNumber extends RuntimeException{
    public IllegalAccountNumber() {
    }

    public IllegalAccountNumber(String message) {
        super(message);
    }

    public IllegalAccountNumber(String message, Throwable cause) {
        super(message, cause);
    }
}
