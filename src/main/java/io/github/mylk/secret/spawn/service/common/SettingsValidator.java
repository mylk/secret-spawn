package io.github.mylk.secret.spawn.service.common;

import io.github.mylk.secret.spawn.exception.SettingNotFoundException;
import io.github.mylk.secret.spawn.enums.Format;
import io.github.mylk.secret.spawn.enums.Source;
import io.github.mylk.secret.spawn.model.Settings;
import org.apache.commons.lang3.NotImplementedException;

public class SettingsValidator {
    public static Boolean validate(Settings settings) throws SettingNotFoundException, NotImplementedException {
        try {
            Format.valueOf(settings.getFormat().toUpperCase());
        } catch (Exception ex) {
            String message = String.format("Format \"%s\" has not been implemented yet.", settings.getFormat());
            throw new NotImplementedException(message);
        }

        try {
            Source.valueOf(settings.getSource().toUpperCase());
        } catch (Exception ex) {
            String message = String.format("Parser \"%s\" has not been implemented yet.", settings.getSource());
            throw new NotImplementedException(message);
        }

        if (settings.getUrl() == null || settings.getUrl().isEmpty()) {
            throw new SettingNotFoundException("The URL of the phrase source could not be found.");
        }

        return true;
    }
}
