package io.github.mylk.secretspawn.manager;

import io.github.mylk.secretspawn.exception.SettingNotFoundException;
import io.github.mylk.secretspawn.model.Settings;
import io.github.mylk.secretspawn.service.common.SettingsReader;
import io.github.mylk.secretspawn.service.common.SettingsValidator;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.NotImplementedException;

public class SettingsManager {
    private Options options;

    public void defineOptions() {
        options = new Options();
        options.addOption("length", true, "The secret's length");
        options.addOption("source", true, "The secret's source [wikipedia]");
        options.addOption("format", true, "The secret's format [simple, hackish]");
        options.addOption("tooRandom", false, "Pick a random phrase out of a paragraph");
    }

    public Settings parse(String[] args) throws ParseException, SettingNotFoundException, NotImplementedException {
        Settings settings = new Settings();
        CommandLine cmd;

        // parse cli settings
        CommandLineParser optionParser = new DefaultParser();
        cmd = optionParser.parse(options, args);

        // used to read default settings from config file
        SettingsReader settingsReader = new SettingsReader();

        Integer length = cmd.hasOption("length")
                ? Integer.parseInt(cmd.getOptionValue("length"))
                : Integer.parseInt(settingsReader.getOptionValue("secret.length"));

        String source = cmd.hasOption("source")
                ? cmd.getOptionValue("source")
                : settingsReader.getOptionValue("secret.source");

        String format = cmd.hasOption("format")
                ? cmd.getOptionValue("format")
                : settingsReader.getOptionValue("secret.format");

        String url = settingsReader.getOptionValue("source.url." + source.toLowerCase());

        Boolean tooRandom = cmd.hasOption("tooRandom");

        settings.setLength(length)
                .setSource(source)
                .setFormat(format)
                .setUrl(url)
                .setIsTooRandom(tooRandom);

        SettingsValidator.validate(settings);

        return settings;
    }

    public Options getOptions() {
        return options;
    }
}
