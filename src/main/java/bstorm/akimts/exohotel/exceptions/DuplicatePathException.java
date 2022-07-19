package bstorm.akimts.exohotel.exceptions;

public class DuplicatePathException extends IllegalStateException{

    public DuplicatePathException() {
        super("cannot create 2 controllers methods with the same path answering to the same HTTP Method request");
    }
}
