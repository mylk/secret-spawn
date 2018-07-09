package io.github.mylk.secretspawn.exception;

public class SettingNotFoundException extends Exception {
    public SettingNotFoundException(Exception ex) {
        super(ex);
    }

    public SettingNotFoundException(String message) {
        super(message);
    }
}
