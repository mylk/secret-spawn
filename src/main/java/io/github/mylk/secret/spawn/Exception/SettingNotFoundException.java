package io.github.mylk.secret.spawn.Exception;

public class SettingNotFoundException extends Exception {
    public SettingNotFoundException(Exception ex) {
        super(ex);
    }

    public SettingNotFoundException(String message) {
        super(message);
    }
}
