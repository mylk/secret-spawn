package io.github.mylk.secret.spawn.service.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OptionsReader
{
    private final String PROPERTIES_FILENAME = "config.properties";

    private final static Properties FILE_OPTIONS = new Properties();

    public OptionsReader()
    {
        if (FILE_OPTIONS.isEmpty()) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
            if (inputStream != null) {
                try {
                    FILE_OPTIONS.load(inputStream);
                    inputStream.close();
                } catch (IOException ex) {
                }
            } else {
                System.out.println("File '" + PROPERTIES_FILENAME + "' not found.");
            }
        }
    }

    public String getOptionValue(String property)
    {
        String value = "";

        try {
            value = FILE_OPTIONS.getProperty(property);
        } catch (Exception ex) {
        }

        return value;
    }
}
