package io.github.mylk.secret.spawn.service.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;

public class OptionsParser
{
    private final String PROPERTY_FILENAME = "config.properties";

    private final static Properties FILE_OPTIONS = new Properties();
    private final CommandLine CLI_OPTIONS;

    public OptionsParser(CommandLine options)
    {
        if (FILE_OPTIONS.isEmpty()) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILENAME);
            if (inputStream != null) {
                try {
                    FILE_OPTIONS.load(inputStream);
                    inputStream.close();
                } catch (IOException ex) {
                }
            } else {
                System.out.println("File '" + PROPERTY_FILENAME + "' not found.");
            }
        }

        CLI_OPTIONS = options;
    }

    public String getFileOptionValue(String property)
    {
        String value = "";

        try {
            value = FILE_OPTIONS.getProperty(property);
        } catch (Exception ex) {
        }

        return value;
    }

    public String getCliOptionValue(String property)
    {
        String value = "";

        if (CLI_OPTIONS.hasOption(property)) {
            value = CLI_OPTIONS.getOptionValue(property);
        }

        return value;
    }

    public String getOption(String fileOptionPropertyName, String cliOptionPropertyName)
    {
        String fileOptionValue = getFileOptionValue(fileOptionPropertyName);
        String cliOptionValue = getCliOptionValue(cliOptionPropertyName);
        return !cliOptionValue.isEmpty() ? cliOptionValue : fileOptionValue;
    }
}
