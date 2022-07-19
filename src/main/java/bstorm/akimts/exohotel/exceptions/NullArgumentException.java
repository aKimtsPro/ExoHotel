package bstorm.akimts.exohotel.exceptions;

public class NullArgumentException extends RuntimeException {

    public NullArgumentException() {
        super("Argument cannot be null");
    }

}
