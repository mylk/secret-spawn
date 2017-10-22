package io.github.mylk.secret.spawn;

import io.github.mylk.secret.spawn.Exception.SettingNotFoundException;
import io.github.mylk.secret.spawn.Manager.SettingsManager;
import io.github.mylk.secret.spawn.Manager.SpawnManager;
import io.github.mylk.secret.spawn.model.Settings;
import io.github.mylk.secret.spawn.model.Secret;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

public class SecretSpawn {
    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.defineOptions();

        // parse cli options
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
        }


        SpawnManager spawnManager = new SpawnManager();
        spawnManager.setSettings(settings);
        Secret secret = spawnManager.spawn();

        System.out.printf("Secret:%n%s%n%n", secret.getContentTransformed());
        System.out.printf("Title:%n%s%n%n", secret.getTitle());
        System.out.printf("Original content:%n%s%n%n", secret.getContentPlain());
        System.out.printf("URL:%n%s%n%n", secret.getUrl());
    }
}
