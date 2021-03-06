package io.github.mylk.secretspawn;

import io.github.mylk.secretspawn.exception.SettingNotFoundException;
import io.github.mylk.secretspawn.exception.SourceCallException;
import io.github.mylk.secretspawn.manager.SettingsManager;
import io.github.mylk.secretspawn.manager.SecretManager;
import io.github.mylk.secretspawn.model.Settings;
import io.github.mylk.secretspawn.model.Secret;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.NotImplementedException;

public class SecretSpawnApplication {
    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.defineOptions();

        // parse settings
        Settings settings = new Settings();
        try {
            settings = settingsManager.parse(args);
        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("secret-spawn", settingsManager.getOptions());
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (SettingNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (NotImplementedException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        SecretManager secretManager = new SecretManager();
        secretManager.setSettings(settings);
        Secret secret = new Secret();
        try {
            secret = secretManager.spawn();
        } catch (SourceCallException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        System.out.printf("Secret:%n%s%n%n", secret.getContentTransformed());
        System.out.printf("Original content:%n%s%n%n", secret.getContentPlain());
        System.out.printf("%s%n%s%n%n", secret.getTitle(), secret.getUrl());
    }
}
