package io.github.mylk.secret.spawn.Exception;

public class SourceCallException extends Exception {
    public SourceCallException(Exception ex) {
        super(ex);
    }

    public SourceCallException(String message) {
        super(message);
    }
}
