package io.github.mylk.secret.spawn;

import io.github.mylk.secret.spawn.exception.SettingNotFoundException;
import io.github.mylk.secret.spawn.exception.SourceCallException;
import io.github.mylk.secret.spawn.manager.SettingsManager;
import io.github.mylk.secret.spawn.manager.SpawnManager;
import io.github.mylk.secret.spawn.model.Settings;
import io.github.mylk.secret.spawn.model.Secret;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.NotImplementedException;

public class SecretSpawn {
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

        SpawnManager spawnManager = new SpawnManager();
        spawnManager.setSettings(settings);
        Secret secret = new Secret();
        try {
            secret = spawnManager.spawn();
        } catch (SourceCallException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        System.out.printf("Secret:%n%s%n%n", secret.getContentTransformed());
        System.out.printf("Original content:%n%s%n%n", secret.getContentPlain());
        System.out.printf("%s%n%s%n%n", secret.getTitle(), secret.getUrl());
    }
}
