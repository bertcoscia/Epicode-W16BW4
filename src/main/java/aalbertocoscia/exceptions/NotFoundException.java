package aalbertocoscia.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("Couldn't find the record with id " + id);
    }
}