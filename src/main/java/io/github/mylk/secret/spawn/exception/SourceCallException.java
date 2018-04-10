package io.github.mylk.secret.spawn.exception;

public class SourceCallException extends Exception {
    public SourceCallException(Exception ex) {
        super(ex);
    }

    public SourceCallException(String message) {
        super(message);
    }
}
