package jira.javarush.com.stub;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}